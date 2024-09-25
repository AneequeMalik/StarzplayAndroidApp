package com.aneeque.starzplayandroidsimulatedvideoapp.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.aneeque.starzplayandroidsimulatedvideoapp.types.MediaType

class MediaTypeDiffCallback : DiffUtil.ItemCallback<MediaType>() {
    override fun areItemsTheSame(oldItem: MediaType, newItem: MediaType): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MediaType, newItem: MediaType): Boolean {
        return oldItem == newItem
    }
}
