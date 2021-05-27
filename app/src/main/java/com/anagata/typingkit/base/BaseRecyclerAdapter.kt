package com.anagata.typingkit.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anagata.typingkit.repository.model.Font

abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder>(
    private val items: ArrayList<T>
) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        TODO("Not yet implemented")
    }

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
