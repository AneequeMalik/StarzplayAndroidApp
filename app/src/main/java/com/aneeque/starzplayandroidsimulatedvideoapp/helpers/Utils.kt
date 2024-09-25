package com.aneeque.starzplayandroidsimulatedvideoapp.helpers

import com.aneeque.domain.retrofit.models.MediaDetails
import com.aneeque.domain.room.MediaDetail

object Utils {
    fun getMoviesList(mediaList: ArrayList<MediaDetails>): ArrayList<MediaDetails> {
        return mediaList.filter { media ->
            media.mediaType == MediaTypes.MOVIE.type
        } as ArrayList<MediaDetails>
    }

    fun getTvShowsList(mediaList: ArrayList<MediaDetails>): ArrayList<MediaDetails> {
        return mediaList.filter { media ->
            media.mediaType == MediaTypes.TV.type
        } as ArrayList<MediaDetails>
    }

    fun getOtherList(mediaList: ArrayList<MediaDetails>): ArrayList<MediaDetails> {
        return mediaList.filter { media ->
            media.mediaType == MediaTypes.OTHER.type
        } as ArrayList<MediaDetails>
    }
}