package com.anagata.typingkit.base

import androidx.recyclerview.widget.RecyclerView
import com.anagata.typingkit.repository.model.Font

abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder>(
    val items: ArrayList<T>
) : RecyclerView.Adapter<VH>() {

    override fun getItemCount() = items.size

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addAll(items: ArrayList<Font>) {
        items.clear()
        items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

}
