package com.anagatatype.app.features.ui.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.anagatatype.app.databinding.DialogSelectionFontSizeBinding


class SelectionFontSizeDialog : DialogFragment() {

    private lateinit var binding: DialogSelectionFontSizeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSelectionFontSizeBinding.inflate(inflater, container, false)
        return binding.root
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

    companion object {
        const val TAG = "SelectionFontSizeDialog"
    }

}