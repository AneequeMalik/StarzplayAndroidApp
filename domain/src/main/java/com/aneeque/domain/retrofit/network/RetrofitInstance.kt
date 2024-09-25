package com.aneeque.domain.retrofit.network

import com.aneeque.domain.BuildConfig
import com.aneeque.domain.retrofit.services.StarzplayServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofitClient: Retrofit.Builder by lazy {
        val levelType = Level.BODY

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(
            Interceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .header("accept", "application/json")
                    .build()
                chain.proceed(request)
            }
        ).build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: StarzplayServices by lazy {
        retrofitClient
            .build()
            .create(StarzplayServices::class.java)
    }
}