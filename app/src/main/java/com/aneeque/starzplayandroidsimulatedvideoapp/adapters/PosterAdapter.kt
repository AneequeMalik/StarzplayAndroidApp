package com.aneeque.starzplayandroidsimulatedvideoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aneeque.domain.retrofit.models.MediaDetails
import com.aneeque.starzplayandroidsimulatedvideoapp.databinding.ItemMediaBinding
import com.aneeque.starzplayandroidsimulatedvideoapp.diffutil.PosterDetailDiffCallback
import com.aneeque.starzplayandroidsimulatedvideoapp.helpers.AppConstants
import com.aneeque.starzplayandroidsimulatedvideoapp.interfaces.MediaItemListeners
import com.bumptech.glide.Glide

class PosterAdapter(private val context: Context, private val listener: MediaItemListeners) :
    ListAdapter<MediaDetails, PosterAdapter.MovieViewHolder>(PosterDetailDiffCallback()) {

    inner class MovieViewHolder(val itemMediaBinding: ItemMediaBinding) :
        RecyclerView.ViewHolder(itemMediaBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val mediaDetail = getItem(position)
        holder.itemMediaBinding.mediaItem.setOnClickListener {
            listener.onMediaItemClick(getItem(position))
        }
        Glide.with(context)
            .load("${AppConstants.MEDIA_BASE_URL}${mediaDetail.posterPath}")
            .into(holder.itemMediaBinding.ivMediaItem)
    }
}
