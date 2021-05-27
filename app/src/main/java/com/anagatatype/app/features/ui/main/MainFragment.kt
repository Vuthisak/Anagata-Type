package com.anagatatype.app.features.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagatatype.app.R
import com.anagatatype.app.base.BaseFragment
import com.anagatatype.app.databinding.FragmentMainBinding
import com.anagatatype.app.features.ui.dialog.SelectionFontSizeDialog

class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setupContent()
    }

    private fun showChooseFontSizeDialog() {
        SelectionFontSizeDialog().show(childFragmentManager, TAG)
    }

    private fun setupContent() {
        setupRecyclerView()
        binding.viewMainToolbar.styleText.setOnClickListener { showChooseFontSizeDialog() }
    }

    private fun setupRecyclerView() {
        binding.mainRecycler.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MainAdapter()
        }
    }

    companion object {
        const val TAG = "MainFragment"
    }

}