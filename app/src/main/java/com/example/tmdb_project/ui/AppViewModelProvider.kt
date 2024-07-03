package com.example.tmdb_project.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tmdb_project.TmdbApplication
import com.example.tmdb_project.ui.theme.screens.Home.HomeViewModel

object AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(tmdbApplication().container.favoriteRepository)
        }
    }
}

fun CreationExtras.tmdbApplication(): TmdbApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TmdbApplication)