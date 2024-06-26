package com.example.tmdb_project

import android.app.Application
import com.example.tmdb_project.data.AppContainer
import com.example.tmdb_project.data.DefaultAppContainer

class TmdbApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}