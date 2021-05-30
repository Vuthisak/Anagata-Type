package com.anagata.typingkit.features.detail

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagata.typingkit.R
import com.anagata.typingkit.base.BaseFragment
import com.anagata.typingkit.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setupContent()
    }

    private fun setupContent() {
        setupFontSizeDropDown()
        binding.detailRecycler.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = DetailAdapter(arrayListOf())
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

}
