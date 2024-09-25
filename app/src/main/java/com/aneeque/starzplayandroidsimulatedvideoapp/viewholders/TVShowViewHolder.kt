package com.aneeque.starzplayandroidsimulatedvideoapp.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aneeque.domain.retrofit.models.MediaDetails
import com.aneeque.starzplayandroidsimulatedvideoapp.R
import com.aneeque.starzplayandroidsimulatedvideoapp.adapters.PosterAdapter
import com.aneeque.starzplayandroidsimulatedvideoapp.databinding.ItemMediaListBinding
import com.aneeque.starzplayandroidsimulatedvideoapp.interfaces.MediaItemListeners

class TVShowViewHolder(private val binding: ItemMediaListBinding, private val listener: MediaItemListeners) :
    RecyclerView.ViewHolder(binding.root), MediaItemListeners {
    fun bind(tvShows: ArrayList<MediaDetails>) {
        binding.layoutTitle.tvMediaTitle.text = itemView.context.getString(R.string.header_tv_shows)
        setupHorizontalRecyclerView(tvShows, binding)
    }

    private fun setupHorizontalRecyclerView(
        list: ArrayList<MediaDetails>,
        binding: ItemMediaListBinding
    ) {
        val posterAdapter = PosterAdapter(binding.root.context, this)
        binding.rvMedia.apply {
            layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = posterAdapter
        }
        posterAdapter.submitList(list)
    }

    override fun onMediaItemClick(mediaDetail: MediaDetails) {
        listener.onMediaItemClick(mediaDetail)
    }
}