package com.aneeque.starzplayandroidsimulatedvideoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aneeque.domain.retrofit.StarzplayRepository
import com.aneeque.domain.retrofit.responses.MediaResponse
import retrofit2.Response

class StarzplaySearchViewModel : ViewModel() {
    private var mediaLiveData = MutableLiveData<Response<MediaResponse>?>()

    fun observeMediaLiveData(
        query: String,
        page: String
    ): MutableLiveData<Response<MediaResponse>?> {
        mediaLiveData = StarzplayRepository().getMedia(query, page)
        return mediaLiveData
    }
}