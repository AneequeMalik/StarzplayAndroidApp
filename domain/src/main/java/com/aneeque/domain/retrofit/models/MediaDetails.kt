package com.aneeque.domain.retrofit.models

import com.aneeque.domain.room.MediaDetail
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MediaDetails(
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("media_type") var mediaType: String? = null,
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("genre_ids") var genreIds: List<Long>? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null
): Serializable {
    override fun toString(): String {
        return "MediaDetails(backdropPath=$backdropPath, id=$id, title=$title, name=$name, originalTitle=$originalTitle, originalName=$originalName, overview=$overview, posterPath=$posterPath, mediaType=$mediaType, "
    }

    // function to convert MediaDetails to MediaDetail
    fun toMediaDetail(): MediaDetail {
        return MediaDetail(
            backdropPath = this.backdropPath ?: "",
            id = this.id.toString(),
            title = this.title ?: this.name ?: "",
            originalTitle = this.originalTitle ?: this.originalName ?: "",
            overview = this.overview ?: "",
            posterPath = this.posterPath ?: "",
            mediaType = this.mediaType ?: "",
            adult = this.adult ?: false,
            originalLanguage = this.originalLanguage ?: "",
            popularity = this.popularity ?: 0.0,
            releaseDate = this.releaseDate ?: "",
            video = this.video ?: false,
            voteAverage = this.voteAverage ?: 0.0,
            voteCount = this.voteCount?.toLong() ?: 0
        )
    }
}
