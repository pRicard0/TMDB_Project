package com.example.tmdb_project.data.network.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BaseURL = "https://api.themoviedb.org/"

    private fun initRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BaseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: TmdbApi = initRetrofit().create(TmdbApi::class.java)
}