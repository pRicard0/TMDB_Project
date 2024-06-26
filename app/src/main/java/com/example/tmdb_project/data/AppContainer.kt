package com.example.tmdb_project.data

import com.example.tmdb_project.network.TmdbApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val tmdbRepository: TmdbRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.themoviedb.org"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: TmdbApiService by lazy {
        retrofit.create(TmdbApiService::class.java)
    }

    override val tmdbRepository: TmdbRepository by lazy {
        NetworkTmdbRepository(retrofit.create(TmdbApiService::class.java))
    }
}