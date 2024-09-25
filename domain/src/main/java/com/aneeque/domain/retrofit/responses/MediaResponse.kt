package com.aneeque.domain.retrofit.responses

import com.aneeque.domain.retrofit.models.MediaDetails
import com.google.gson.annotations.SerializedName

data class MediaResponse(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MediaDetails> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)