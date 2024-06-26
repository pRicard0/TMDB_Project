package com.example.tmdb_project.network
import android.media.Image
import com.example.tmdb_project.model.MovieImagesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApiService {
    @GET("/3/movie/{movie_id}/images")
    suspend fun getMovieImage(@Path("movie_id") movieId: Int): MovieImagesResponse
}

