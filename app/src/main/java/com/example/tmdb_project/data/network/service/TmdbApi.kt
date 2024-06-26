package com.example.tmdb_project.data.network.service

import com.example.tmdb_project.data.network.response.TopMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Headers

private const val envVar: String = "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTQwNzYzZDQ5NDY0YTE1OTIyMmRiNGIwZDFmMWYxNCIsIm5iZiI6MTcxOTI2ODkxMS4wNjc5Niwic3ViIjoiNjY3OTYzZDdkYmM5NTZkNDdmOWY0NjViIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.d52ua3AhPh3GH35RZp8tPF_hGAyirOSoUasG3ukx9_g"

interface TmdbApi {
    @Headers(envVar, "accept: application/json")
    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(): TopMoviesResponse
}