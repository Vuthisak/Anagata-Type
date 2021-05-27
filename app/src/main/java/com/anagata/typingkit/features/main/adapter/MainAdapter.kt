package com.anagata.typingkit.features.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemFontBinding
import com.anagata.typingkit.repository.model.Font

class MainAdapter(
    private val onItemClickListener: (font: Font) -> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val items = arrayListOf<Font>()

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
            contentContainer.setOnClickListener {
                onItemClickListener(Font("Test", 1, "Test"))
            }
        }
    }

    override fun getItemCount(): Int = 10

    fun add(item: Font) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addAll(items: ArrayList<Font>) {
        items.clear()
        items.addAll(items)
        notifyDataSetChanged()
    }

    class MainViewHolder(
        val binding: ViewItemFontBinding
    ) : BaseViewHolder(binding)

}