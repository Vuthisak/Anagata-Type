package com.anagata.typingkit.features.main

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anagata.typingkit.base.BaseRecyclerAdapter
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemFontBinding
import com.anagata.typingkit.repository.model.FontWeight
import com.anagata.typingkit.repository.model.MockFont
import java.io.File

class MainAdapter(
    private val fontWeight: FontWeight?,
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
            fontWeight?.location?.let {
                val typeface = Typeface.createFromFile(File(it))
                contentText.typeface = typeface
            }
            listTitleText.text = item.title ?: ""
            contentText.text = item.content
            contentContainer.setOnClickListener {
            }
        }
    }

    class MainViewHolder(
        val binding: ViewItemFontBinding
    ) : BaseViewHolder(binding)

}