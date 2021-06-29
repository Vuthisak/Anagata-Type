package com.anagata.typingkit.features.main

import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anagata.typingkit.base.BaseRecyclerAdapter
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemFontBinding
import com.anagata.typingkit.repository.model.MockFont
import com.anagata.typingkit.util.getDefaultValue
import com.anagata.typingkit.util.gone
import com.anagata.typingkit.util.visible
import com.google.android.flexbox.*
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
            val context = root.context
            val item = mockFonts[position]
            listTitleText.text = item.title.getDefaultValue()
            if (position == 0) {
                val chars = item.content
                    .replace(" ", "")
                    .replace("\n", "")
                    .toCharArray()
                val layoutManager = FlexboxLayoutManager(context)
                layoutManager.run {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                    alignItems = AlignItems.CENTER
                    justifyContent = JustifyContent.CENTER
                }
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = CharRecyclerAdapter(chars, fontSize, fontLocation)
                contentText.gone()
                recyclerView.visible()
            } else {
                recyclerView.gone()
                contentText.visible()
                fontLocation?.let {
                    contentText.typeface = Typeface.createFromFile(File(it))
                    contentText.setTextSize(TypedValue.COMPLEX_UNIT_PT, fontSize)
                }
                contentText.text = item.content
            }
            contentContainer.setOnClickListener {
            }
        }
    }

    class MainViewHolder(
        val binding: ViewItemFontBinding
    ) : BaseViewHolder(binding)

}