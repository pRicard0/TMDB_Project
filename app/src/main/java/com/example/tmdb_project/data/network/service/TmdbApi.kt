package com.example.tmdb_project.data.network.service

import com.example.tmdb_project.data.network.response.TopMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Headers

private const val envVar: String = "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ODBjNDQxMDhhYmJjMGYyMDkxOTE5ZjAzNzhmNmZiYiIsIm5iZiI6MTcxOTMyNjc0MS4yNjU1OTksInN1YiI6IjY0MzQ3YzZmZTkyZDgzMDExMzA4OWFkNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.n73f9WOCLrrdbA8YjGglC4Lw3E6G_yw3kcN_Sg1MjSw"

interface TmdbApi {
    @Headers(envVar, "accept: application/json")
    @GET("3/movie/popular")
    suspend fun getTopRatedMovies(): TopMoviesResponse
}