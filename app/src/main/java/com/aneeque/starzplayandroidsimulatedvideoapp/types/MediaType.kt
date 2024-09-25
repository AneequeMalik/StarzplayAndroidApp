package com.aneeque.starzplayandroidsimulatedvideoapp.types

import com.aneeque.domain.retrofit.models.MediaDetails
sealed class MediaType {
    data class Movie(val movies: ArrayList<MediaDetails>) : MediaType()
    data class TVShow(val tvShows: ArrayList<MediaDetails>) : MediaType()
    data class Other(val others: ArrayList<MediaDetails>) : MediaType()
}
