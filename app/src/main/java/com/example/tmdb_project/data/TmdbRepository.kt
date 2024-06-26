package com.example.tmdb_project.data

import com.example.tmdb_project.model.MovieImagesResponse
import com.example.tmdb_project.network.TmdbApiService

interface TmdbRepository {
    suspend fun getMovieImage(movieId: Int): MovieImagesResponse
}

class NetworkTmdbRepository(
    private val tmdbApiService: TmdbApiService
) : TmdbRepository {
    override suspend fun getMovieImage(movieId: Int): MovieImagesResponse {
        return tmdbApiService.getMovieImage(movieId)
    }
}