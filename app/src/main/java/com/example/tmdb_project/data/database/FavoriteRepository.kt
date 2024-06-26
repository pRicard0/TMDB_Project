package com.example.tmdb_project.data.database

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllPostersStream(): Flow<List<String>>

    suspend fun insertFavorite(favorite: Favorite)

    suspend fun deleteFavorite(favorite: Favorite)
}