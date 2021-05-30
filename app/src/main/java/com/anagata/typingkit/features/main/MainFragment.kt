package com.anagata.typingkit.features.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagata.typingkit.R
import com.anagata.typingkit.base.BaseFragment
import com.anagata.typingkit.databinding.FragmentMainBinding
import com.anagata.typingkit.features.detail.DetailActivity
import com.anagata.typingkit.features.dialog.SelectionFontSizeDialog
import com.anagata.typingkit.features.splash.SplashActivity
import com.anagata.typingkit.repository.model.MockFont
import com.anagata.typingkit.repository.model.Typeface
import com.anagata.typingkit.util.getMockFonts

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private lateinit var vm: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var typeface: Typeface
    private var mockFonts = arrayListOf<MockFont>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.intent?.getSerializableExtra(SplashActivity.KEY_TYPEFACE)?.let {
            if (it is Typeface) typeface = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        getMockFonts(requireContext())?.mockFonts?.apply {
            mockFonts.addAll(this)
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is SelectionFontSizeDialog) {
            fragment.onApplyClickListener {
                showToast("${it.first}, ${it.second}")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setupContent()
        registerListener()
    }

    private fun registerListener() {
        binding.refreshButton.setOnClickListener {
            vm.getData()
        }
        binding.viewMainToolbar.styleText.setOnClickListener { showChooseFontSizeDialog() }
    }

    private fun showChooseFontSizeDialog() {
        typeface.run {
            SelectionFontSizeDialog.newInstance(fontNames)
                .show(childFragmentManager, SelectionFontSizeDialog.TAG)
        }
    }

    private fun setupContent() {
        binding.run {
            typeface.typefaces.firstOrNull()?.let {
                val subTitle = "${it.name} + 12pt"
                viewMainToolbar.subTitleText.text = subTitle
            }
            setupRecyclerView()
        }
    }

    private fun setupRecyclerView() {
        binding.mainRecycler.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MainAdapter(mockFonts) {
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

        fun newInstance(typeface: Typeface): MainFragment {
            return MainFragment().apply {
                val bundle = Bundle()
                bundle.putSerializable(SplashActivity.KEY_TYPEFACE, typeface)
                arguments = bundle
            }
        }
    }

}