package com.aneeque.starzplayandroidsimulatedvideoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aneeque.domain.room.MediaDetail
import com.aneeque.starzplayandroidsimulatedvideoapp.databinding.ItemRecentsBinding
import com.aneeque.starzplayandroidsimulatedvideoapp.helpers.AppConstants
import com.bumptech.glide.Glide

class RecentItemsAdapter(
    private val context: Context,
    private val list: List<MediaDetail>
) : RecyclerView.Adapter<RecentItemsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecentsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecentsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        Glide.with(context).load(AppConstants.MEDIA_BASE_URL + item.posterPath)
            .into(holder.binding.imgRecentItem)
        holder.binding.tvRecentItem.text = item.title ?: "NAME NOT FOUND"
        holder.binding.tvRecentDescription.text = item.overview ?: "DESCRIPTION NOT FOUND"
    }

}