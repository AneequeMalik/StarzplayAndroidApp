package com.aneeque.starzplayandroidsimulatedvideoapp.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.aneeque.domain.retrofit.models.MediaDetails

class PosterDetailDiffCallback : DiffUtil.ItemCallback<MediaDetails>() {
    override fun areItemsTheSame(oldItem: MediaDetails, newItem: MediaDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MediaDetails, newItem: MediaDetails): Boolean {
        return oldItem == newItem
    }
}