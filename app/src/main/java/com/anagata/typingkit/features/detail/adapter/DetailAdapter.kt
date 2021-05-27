package com.anagata.typingkit.features.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.anagata.typingkit.base.BaseRecyclerAdapter
import com.anagata.typingkit.base.BaseViewHolder
import com.anagata.typingkit.databinding.ViewItemPreviewFontBinding
import com.anagata.typingkit.repository.model.Font

class DetailAdapter(
    private val items: ArrayList<Font>
) : BaseRecyclerAdapter<Font, DetailAdapter.DetailViewHolder>(items) {

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = ViewItemPreviewFontBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DetailViewHolder(binding)
    }

    override fun getItemCount(): Int = 6

    class DetailViewHolder(
        private val binding: ViewItemPreviewFontBinding
    ) : BaseViewHolder(binding)
}