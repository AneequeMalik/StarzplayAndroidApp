package com.aneeque.domain.retrofit.services

import com.aneeque.domain.BuildConfig
import com.aneeque.domain.retrofit.responses.MediaResponse
import com.aneeque.domain.utils.constants.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StarzplayServices {
    @GET(API.MULTI_SEARCH)
    fun fetchSearchResults(
        @Query("query") query: String,
        @Query("page") page: String,
        @Query("api_key", encoded = true) apiKey: String = BuildConfig.API_KEY
    ): Call<MediaResponse>
}