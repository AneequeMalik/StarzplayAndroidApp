package com.aneeque.domain.retrofit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.aneeque.domain.retrofit.network.RetrofitInstance
import com.aneeque.domain.retrofit.responses.MediaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarzplayRepository {
    private var mediaLiveData = MutableLiveData<Response<MediaResponse>?>()

    fun getMedia(query: String, page: String): MutableLiveData<Response<MediaResponse>?> {
        val call = RetrofitInstance.apiInterface.fetchSearchResults(query, page)
        call.enqueue(object : Callback<MediaResponse> {
            override fun onResponse(call: Call<MediaResponse>, response: Response<MediaResponse>) {
                mediaLiveData.value = response
                Log.e("OkHttps", "response: $response")
            }

            override fun onFailure(call: Call<MediaResponse>, t: Throwable) {
                mediaLiveData.value = null
                Log.e("OkHttps", "ERROR MESSAGE: ${t.message}")
            }

        })

        return mediaLiveData
    }
}