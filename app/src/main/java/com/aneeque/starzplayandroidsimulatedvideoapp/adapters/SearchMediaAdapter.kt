package com.aneeque.starzplayandroidsimulatedvideoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aneeque.domain.retrofit.models.MediaDetails
import com.aneeque.starzplayandroidsimulatedvideoapp.databinding.ItemMediaListBinding
import com.aneeque.starzplayandroidsimulatedvideoapp.diffutil.MediaTypeDiffCallback
import com.aneeque.starzplayandroidsimulatedvideoapp.interfaces.MediaItemListeners
import com.aneeque.starzplayandroidsimulatedvideoapp.types.MediaType
import com.aneeque.starzplayandroidsimulatedvideoapp.viewholders.MovieViewHolder
import com.aneeque.starzplayandroidsimulatedvideoapp.viewholders.OthersViewHolder
import com.aneeque.starzplayandroidsimulatedvideoapp.viewholders.TVShowViewHolder

class SearchMediaAdapter(private val listener: MediaItemListeners) :
    ListAdapter<MediaType, RecyclerView.ViewHolder>(MediaTypeDiffCallback()), MediaItemListeners {
    private var mediaTypeList: List<MediaType> = listOf()

    companion object {
        private const val ITEM_TYPE_MOVIE = 0
        private const val ITEM_TYPE_TV = 1
        private const val ITEM_TYPE_OTHER = 2
    }

    fun submitList(list: ArrayList<MediaType>) {
        this.mediaTypeList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MediaType.Movie -> ITEM_TYPE_MOVIE
            is MediaType.TVShow -> ITEM_TYPE_TV
            is MediaType.Other -> ITEM_TYPE_OTHER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemMediaListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return when (viewType) {
            ITEM_TYPE_MOVIE -> MovieViewHolder(binding, listener = this)
            ITEM_TYPE_TV -> TVShowViewHolder(binding, listener = this)
            ITEM_TYPE_OTHER -> OthersViewHolder(binding, listener = this)
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is MediaType.Movie -> {
                val moviesList = ArrayList<MediaDetails>()
                moviesList.addAll(item.movies)
                val movieViewHolder = (holder as MovieViewHolder)
                if (item.movies.isEmpty()) {
                    movieViewHolder.itemView.visibility = View.GONE
                } else {
                    movieViewHolder.itemView.visibility = View.VISIBLE
                    movieViewHolder.bind(item.movies)
                }
            }

            is MediaType.TVShow -> {
                val tvShowList = ArrayList<MediaDetails>()
                tvShowList.addAll(item.tvShows)
                val tvShowViewHolder = (holder as TVShowViewHolder)
                if (item.tvShows.isEmpty()) {
                    tvShowViewHolder.itemView.visibility = View.GONE
                } else {
                    tvShowViewHolder.itemView.visibility = View.VISIBLE
                    tvShowViewHolder.bind(item.tvShows)
                }
            }

            is MediaType.Other -> {
                val othersList = ArrayList<MediaDetails>()
                othersList.addAll(item.others)
                val otherViewHolder = (holder as OthersViewHolder)
                if (item.others.isEmpty()) {
                    otherViewHolder.itemView.visibility = View.GONE
                } else {
                    otherViewHolder.itemView.visibility = View.VISIBLE
                    otherViewHolder.bind(item.others)
                }
            }
        }
    }

    override fun onMediaItemClick(mediaDetail: MediaDetails) {
        listener.onMediaItemClick(mediaDetail)
    }
}