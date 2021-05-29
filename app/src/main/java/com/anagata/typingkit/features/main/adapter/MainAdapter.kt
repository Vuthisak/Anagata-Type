package com.anagata.typingkit.features.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anagata.typingkit.base.BaseRecyclerAdapter
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemFontBinding
import com.anagata.typingkit.repository.model.Font
import com.anagata.typingkit.repository.model.MockFont

class MainAdapter(
    private val mockFonts: ArrayList<MockFont>,
    private val onItemClickListener: (mockFont: MockFont) -> Unit
) : BaseRecyclerAdapter<MockFont, MainAdapter.MainViewHolder>(mockFonts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ViewItemFontBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.run {
            val item = mockFonts[position]
            listTitleText.text = item.title ?: ""
            contentContainer.setOnClickListener {
            }
        }
    }

    class MainViewHolder(
        val binding: ViewItemFontBinding
    ) : BaseViewHolder(binding)

}