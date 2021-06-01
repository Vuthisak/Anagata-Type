package com.anagata.typingkit.features.main

import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anagata.typingkit.base.BaseRecyclerAdapter
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemFontBinding
import com.anagata.typingkit.repository.model.Style
import com.anagata.typingkit.repository.model.MockFont
import com.anagata.typingkit.util.getDefaultValue
import java.io.File

class MainAdapter(
    private val fontSize: Float,
    private val fontLocation: String?,
    private val mockFonts: ArrayList<MockFont>
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
            fontLocation?.let {
                contentText.typeface = Typeface.createFromFile(File(it))
                contentText.setTextSize(TypedValue.COMPLEX_UNIT_PT, fontSize)
            }
            listTitleText.text = item.title.getDefaultValue()
            contentText.text = item.content
            contentContainer.setOnClickListener {
            }
        }
    }

    class MainViewHolder(
        val binding: ViewItemFontBinding
    ) : BaseViewHolder(binding)

}