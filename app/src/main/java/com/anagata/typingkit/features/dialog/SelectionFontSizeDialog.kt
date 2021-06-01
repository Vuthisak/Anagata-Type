package com.anagata.typingkit.features.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.anagata.typingkit.R
import com.anagata.typingkit.databinding.DialogSelectionFontSizeBinding
import com.anagata.typingkit.features.main.MainState
import com.anagata.typingkit.features.main.MainViewModel
import com.anagata.typingkit.repository.model.FontSelected
import com.anagata.typingkit.repository.model.Typeface
import org.koin.androidx.viewmodel.ext.android.viewModel

typealias OnClickListener<T> = (T) -> Unit

class SelectionFontSizeDialog : DialogFragment() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: DialogSelectionFontSizeBinding
    private val fontNames = arrayListOf<String>()
    private var fonts = arrayListOf<Pair<String, String>>()
    private var fontSelected = FontSelected("", "", 0, 0, "")
    private var onApplyClickListener: OnClickListener<FontSelected>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSelectionFontSizeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObserver()
        viewModel.getFonts()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.run {
            setLayout(MATCH_PARENT, WRAP_CONTENT)
        }
    }

    fun onApplyClickListener(onApplyClickListener: OnClickListener<FontSelected>) {
        this.onApplyClickListener = onApplyClickListener
    }

    private fun registerObserver() {
        viewModel.liveData.observe(requireActivity()) {
            when (it) {
                is MainState.OnGetTypeFaceSuccess -> {
                    fonts = Typeface.getFontNames(it.fonts)
                    setupContents(fonts)
                }
            }
        }
    }

    private fun setupContents(fonts: ArrayList<Pair<String, String>>) {
        setupFontStyleDropDown(fonts)
        setupFontSizeDropDown()
        registerListener()
    }

    private fun registerListener() {
        binding.applyButton.setOnClickListener {
            val first = binding.fontStyleAutoCompleteText.text.toString()
            val second = binding.sizeAutoCompleteText.text.toString()
            val fontIndex = fontNames.indexOf(first)
            val fontSizeIndex = resources.getStringArray(R.array.sizes).indexOf(second)
            fontSelected =
                FontSelected(
                    "$first + $second",
                    second,
                    fontIndex,
                    fontSizeIndex,
                    fonts[fontIndex].second
                )
            this.onApplyClickListener?.invoke(fontSelected)
            dismiss()
        }
    }

    private fun setupFontStyleDropDown(fonts: ArrayList<Pair<String, String>>) {
        fonts.forEach { fontNames.add(it.first) }
        val adapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, fontNames)
        binding.fontStyleAutoCompleteText.run {
            setAdapter(adapter)
            setText(adapter.getItem(fontSelected.fontIndex), false)
        }
    }

    private fun setupFontSizeDropDown() {
        val sizes = requireContext().resources.getStringArray(R.array.sizes)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, sizes)
        binding.sizeAutoCompleteText.run {
            setAdapter(adapter)
            setText(adapter.getItem(fontSelected.fontSizeIndex), false)
        }

    }

    private fun initArguments() {
        requireArguments().run {
            getSerializable(ARG_FONT_SELECTED)?.run {
                if (this is FontSelected) {
                    fontSelected = this
                }
            }
        }
    }

    companion object {
        const val TAG = "SelectionFontSizeDialog"
        private const val ARG_FONT_SELECTED = "fontSelectedArg"

        fun newInstance(fontSelected: FontSelected?): SelectionFontSizeDialog {
            return SelectionFontSizeDialog().apply {
                val bundle = Bundle()
                bundle.putSerializable(ARG_FONT_SELECTED, fontSelected)
                arguments = bundle
            }
        }
    }

}