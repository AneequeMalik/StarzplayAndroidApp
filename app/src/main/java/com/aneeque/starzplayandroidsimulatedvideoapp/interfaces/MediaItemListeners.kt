package com.aneeque.starzplayandroidsimulatedvideoapp.interfaces

import com.aneeque.domain.retrofit.models.MediaDetails

interface MediaItemListeners {
    fun onMediaItemClick(mediaDetail: MediaDetails)
}