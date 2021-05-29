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

class SelectionFontSizeDialog : DialogFragment() {

    private lateinit var binding: DialogSelectionFontSizeBinding
    private val fontNames = arrayListOf<String>()

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

    private fun setupContents() {
        setupFontStyleDropDown()
    }

    private fun setupFontStyleDropDown() {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, fontNames)
        binding.fontStyleAutoCompleteText.setAdapter(adapter)
    }

    private fun setupFontSizeDropDown() {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, fontNames)
        binding.sizeAutoCompleteText.setAdapter(adapter)
    }

    private fun initArguments() {
        requireArguments().getStringArrayList(ARG_FONT_NAMES)?.run {
            fontNames.addAll(this)
        }
    }

    companion object {
        const val TAG = "SelectionFontSizeDialog"
        private const val ARG_FONT_NAMES = "ArgFontNames"

        fun newInstance(fontNames: ArrayList<String>): SelectionFontSizeDialog {
            return SelectionFontSizeDialog().apply {
                val bundle = Bundle()
                bundle.putStringArrayList(ARG_FONT_NAMES, fontNames)
                arguments = bundle
            }
        }
    }

}