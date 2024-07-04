package com.example.tmdb_project.data.database

import com.example.tmdb_project.data.network.response.CardResponse
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllPostersStream(): Flow<List<String>>

    suspend fun getFavoriteByPosterPath(poster: String): Favorite

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)
}