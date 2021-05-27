package com.anagatatype.app.features.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anagatatype.app.base.BaseViewHolder
import com.anagatatype.app.databinding.ViewItemFontBinding
import com.anagatatype.app.repository.model.Font

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

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
        private val binding: ViewItemFontBinding
    ) : BaseViewHolder(binding)

}