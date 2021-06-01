package com.anagata.typingkit.features.detail

import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anagata.typingkit.base.BaseRecyclerAdapter
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemPreviewFontBinding
import com.anagata.typingkit.repository.model.Font
import com.anagata.typingkit.repository.model.Style
import java.io.File

class DetailAdapter(
    private val previewString: String,
    private val fontSize: Float,
    private val styles: List<Style>
) : BaseRecyclerAdapter<Style, DetailAdapter.DetailViewHolder>(styles as ArrayList<Style>) {

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.binding.run {
            val style = styles[position]
            appearanceText.text = style.weight
            previewText.run {
                style.location?.let { typeface = Typeface.createFromFile(File(it)) }
                setTextSize(TypedValue.COMPLEX_UNIT_PT, fontSize)
                text = previewString
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = ViewItemPreviewFontBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailViewHolder(binding)
    }

    class DetailViewHolder(
        val binding: ViewItemPreviewFontBinding
    ) : BaseViewHolder(binding)
}