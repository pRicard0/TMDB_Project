package com.example.tmdb_project

import androidx.compose.runtime.Composable
import com.example.tmdb_project.ui.theme.screens.HomeScreen
import com.example.tmdb_project.viewmodel.TmdbViewModel

@Composable
fun TmdbApp() {
    HomeScreen(tmdbApiStatus = TmdbViewModel.TmdbApiStatus, retryAction = { /*TODO*/ })
}