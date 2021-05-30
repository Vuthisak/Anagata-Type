package com.anagata.typingkit.features.detail

import android.os.Bundle
import android.view.View
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
        binding.detailRecycler.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = DetailAdapter(arrayListOf())
        }
    }

}
