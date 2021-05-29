package com.anagata.typingkit.features.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagata.typingkit.R
import com.anagata.typingkit.base.BaseFragment
import com.anagata.typingkit.databinding.FragmentMainBinding
import com.anagata.typingkit.features.detail.activity.DetailActivity
import com.anagata.typingkit.features.dialog.SelectionFontSizeDialog
import com.anagata.typingkit.features.main.adapter.MainAdapter
import com.anagata.typingkit.features.main.state.MainState
import com.anagata.typingkit.features.main.viewmodel.MainViewModel
import com.anagata.typingkit.util.helper.FirebaseRepository

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private lateinit var vm: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setupContent()
        registerListener()
        registerObserver()
    }

    private fun registerObserver() {
        vm.liveData.observe(requireActivity()) {
            if (it == null) return@observe
            when (it) {
                is MainState.OnGetTypeFaceSuccess -> showToast(it.typeface.typefaces.size.toString())
                is MainState.OnError -> {
                    showToast("Error")
                }
            }
        }
    }

    private fun registerListener() {
        binding.refreshButton.setOnClickListener {
            vm.getData()
        }
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