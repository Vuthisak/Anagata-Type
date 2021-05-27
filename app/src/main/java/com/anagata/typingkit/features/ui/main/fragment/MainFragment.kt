package com.anagata.typingkit.features.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagata.typingkit.R
import com.anagata.typingkit.base.BaseFragment
import com.anagata.typingkit.databinding.FragmentMainBinding
import com.anagata.typingkit.features.ui.detail.DetailActivity
import com.anagata.typingkit.features.ui.dialog.SelectionFontSizeDialog
import com.anagata.typingkit.features.ui.main.adapter.MainAdapter

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

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
            adapter = MainAdapter {
                openDetailActivity()
            }
        }
    }

    private fun openDetailActivity() {
        requireContext().run {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val TAG = "MainFragment"
    }

}