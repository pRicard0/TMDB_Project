package com.example.tmdb_project.data.database

import kotlinx.coroutines.flow.Flow

class OfflineFavoriteRepository(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getAllPostersStream(): Flow<List<String>> = favoriteDao.getAllPosters()

    override suspend fun getFavoriteByPosterPath(poster: String): Favorite = favoriteDao.getFavoriteByPosterPath(poster)

    override suspend fun insertFavorite(favorite: Favorite) = favoriteDao.insertFavorite(favorite)

    override suspend fun deleteFavorite(favorite: Favorite) = favoriteDao.deleteFavorite(favorite)
}