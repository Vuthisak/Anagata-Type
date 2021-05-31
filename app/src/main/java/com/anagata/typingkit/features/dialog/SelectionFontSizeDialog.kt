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

typealias OnClickListener<T> = (T) -> Unit

class SelectionFontSizeDialog : DialogFragment() {

    private lateinit var binding: DialogSelectionFontSizeBinding
    private val fontNames = arrayListOf<String>()
    private var defaultPos: Int = 0
    private var onApplyClickListener: OnClickListener<Pair<Int, String>>? = null

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
        setupContents()
        registerListener()
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

    fun onApplyClickListener(onApplyClickListener: OnClickListener<Pair<Int, String>>) {
        this.onApplyClickListener = onApplyClickListener
    }

    private fun registerListener() {
        binding.applyButton.setOnClickListener {
            val first = binding.fontStyleAutoCompleteText.text.toString()
            val second = binding.sizeAutoCompleteText.text.toString()
            this.onApplyClickListener?.invoke(Pair(fontNames.indexOf(first), second))
            dismiss()
        }
    }

    private fun setupContents() {
        setupFontStyleDropDown()
        setupFontSizeDropDown()
    }

    private fun setupFontStyleDropDown() {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, fontNames)
        binding.fontStyleAutoCompleteText.run {
            setAdapter(adapter)
            setText(adapter.getItem(defaultPos), false)
        }
    }

    private fun setupFontSizeDropDown() {
        val sizes = requireContext().resources.getStringArray(R.array.sizes)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, sizes)
        binding.sizeAutoCompleteText.run {
            setAdapter(adapter)
            setText(adapter.getItem(0), false)
        }

    }

    private fun initArguments() {
        requireArguments().run {
            getStringArrayList(ARG_FONT_NAMES)?.run {
                fontNames.addAll(this)
            }
            getInt(ARG_DEFAULT_POS).run {
                defaultPos = this
            }
        }
    }

    companion object {
        const val TAG = "SelectionFontSizeDialog"
        private const val ARG_FONT_NAMES = "ArgFontNames"
        private const val ARG_DEFAULT_POS = "ArgDefaultPos"

        fun newInstance(
            fontNames: ArrayList<String>,
            defaultPos: Int = 0
        ): SelectionFontSizeDialog {
            return SelectionFontSizeDialog().apply {
                val bundle = Bundle()
                bundle.putStringArrayList(ARG_FONT_NAMES, fontNames)
                bundle.putInt(ARG_DEFAULT_POS, defaultPos)
                arguments = bundle
            }
        }
    }

}