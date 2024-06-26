package com.example.tmdb_project

import android.app.Application
import com.example.tmdb_project.data.database.AppContainer
import com.example.tmdb_project.data.database.AppDataContainer

class TmdbApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}