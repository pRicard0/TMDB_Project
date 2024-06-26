package com.example.tmdb_project.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterPath: String
)