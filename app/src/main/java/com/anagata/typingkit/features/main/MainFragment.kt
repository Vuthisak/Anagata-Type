package com.anagata.typingkit.features.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagata.typingkit.R
import com.anagata.typingkit.base.BaseFragment
import com.anagata.typingkit.databinding.FragmentMainBinding
import com.anagata.typingkit.features.detail.DetailActivity
import com.anagata.typingkit.features.dialog.SelectionFontSizeDialog
import com.anagata.typingkit.repository.model.Font
import com.anagata.typingkit.repository.model.FontSelected
import com.anagata.typingkit.repository.model.MockFont
import com.anagata.typingkit.util.defaultSize
import com.anagata.typingkit.util.getMockFonts
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding
    private var mockFonts = arrayListOf<MockFont>()
    private var fonts = listOf<Font>()
    private lateinit var currentFont: Font
    private var fontSelected: FontSelected? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMockFonts(requireContext())?.mockFonts?.apply {
            mockFonts.addAll(this)
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is SelectionFontSizeDialog) {
            fragment.onApplyClickListener {
                it.font?.run { currentFont = this }
                fontSelected = it
                binding.viewMainToolbar.subTitleText.text = it.fontStyle
                setupRecyclerView(it.location)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        registerObserver()
        viewModel.getFonts()
    }

    private fun registerObserver() {
        viewModel.liveData.observe(requireActivity()) {
            when (it) {
                is MainState.OnGetTypeFaceSuccess -> {
                    this.fonts = it.fonts
                    setupContent(it.fonts)
                    registerListener()
                }
            }
        }
    }

    private fun registerListener() {
        binding.run {
            viewMainToolbar.run {
                subTitleText.setOnClickListener {
                    showChooseFontSizeDialog()
                }
                styleText.setOnClickListener {
                    DetailActivity.start(requireContext(), currentFont)
                }
            }
            refreshButton.setOnClickListener {
                mainRecycler.scrollToPosition(0)
            }
        }
    }

    private fun showChooseFontSizeDialog() {
        SelectionFontSizeDialog
            .newInstance(fontSelected)
            .show(childFragmentManager, SelectionFontSizeDialog.TAG)
    }

    private fun setupContent(fonts: List<Font>) {
        binding.run {
            fonts.firstOrNull()?.let {
                currentFont = it
                it.styles.firstOrNull()?.let { style ->
                    val subTitle = "${it.name} ${style.name} + ${defaultSize.toInt()} pt"
                    viewMainToolbar.subTitleText.text = subTitle
                    setupRecyclerView(style.location)
                }
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupRecyclerView(fontLocation: String?, fontSize: Float = defaultSize) {
        binding.mainRecycler.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MainAdapter(fontSize, fontLocation, mockFonts)
        }
    }

}
