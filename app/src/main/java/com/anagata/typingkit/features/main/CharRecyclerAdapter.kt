package com.anagata.typingkit.features.main

import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.anagata.typingkit.base.BaseRecyclerAdapter
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemChartBinding
import com.anagata.typingkit.util.defaultSize
import java.io.File

class CharRecyclerAdapter(
    chars: CharArray,
    private val fontLocation: String?,
) : BaseRecyclerAdapter<Char, CharRecyclerAdapter.ViewHolder>(chars.toList() as ArrayList<Char>) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewItemChartBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.run {
            fontLocation?.let {
                typeface = Typeface.createFromFile(File(it))
                setTextSize(TypedValue.COMPLEX_UNIT_PT, defaultSize)
            }
            text = items[position].toString()
        }
    }

    class ViewHolder(val binding: ViewItemChartBinding) : BaseViewHolder(binding)

}