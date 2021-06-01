package com.anagata.typingkit.features.detail

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagata.typingkit.R
import com.anagata.typingkit.base.BaseFragment
import com.anagata.typingkit.databinding.FragmentDetailBinding
import com.anagata.typingkit.repository.model.Font
import com.anagata.typingkit.repository.model.FontSelected
import com.anagata.typingkit.util.defaultSize

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var font: Font

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setArg()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setupContent()
    }

    private fun setArg() {
        font = requireActivity().intent?.extras?.getSerializable(DetailActivity.FONT_KEY) as Font
    }

    private fun setupContent() {
        setupFontSizeDropDown()
        binding.detailRecycler.layoutManager = LinearLayoutManager(requireContext())
        setAdapter()
        binding.previewInputText.doOnTextChanged { _, _, _, _ ->
            setAdapter()
        }

    }

    private fun setAdapter() {
        binding.detailRecycler.adapter = DetailAdapter(
            binding.previewInputText.text.toString(),
            FontSelected.getFontSize(binding.sizeAutoCompleteText.text.toString()),
            font.styles
        )
    }

    private fun setupFontSizeDropDown() {
        val sizes = requireContext().resources.getStringArray(R.array.sizes)
        val adapter = ArrayAdapter(requireContext(), R.layout.item_drop_down, sizes)
        binding.sizeAutoCompleteText.run {
            setAdapter(adapter)
            setText(adapter.getItem(0), false)
        }
        binding.sizeAutoCompleteText.setOnItemClickListener { _, _, _, _ ->
            setAdapter()
        }
    }

}
