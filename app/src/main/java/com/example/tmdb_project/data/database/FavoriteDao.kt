package com.example.tmdb_project.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT posterPath FROM favorite")
    fun getAllPosters(): Flow<List<String>>

    @Query("SELECT * FROM favorite WHERE posterPath = :posterPath")
    suspend fun getFavoriteByPosterPath(posterPath: String): Favorite

    @Query("SELECT id FROM favorite WHERE id = :id")
    suspend fun getFavoriteById(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}