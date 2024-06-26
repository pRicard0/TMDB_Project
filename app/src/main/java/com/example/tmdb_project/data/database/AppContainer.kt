package com.example.tmdb_project.data.database

import android.content.Context

interface AppContainer {
    val favoriteRepository: FavoriteRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val favoriteRepository : FavoriteRepository by lazy {
        OfflineFavoriteRepository(FavoriteDatabase.getDatabase(context).favoriteDao())
    }
}