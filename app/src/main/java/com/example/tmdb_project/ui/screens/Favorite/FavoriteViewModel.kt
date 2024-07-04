package com.example.tmdb_project.ui.screens.Favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_project.data.database.Favorite
import com.example.tmdb_project.data.database.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository): ViewModel() {

    suspend fun unfavoriteMovie(movie: Favorite) {
        favoriteRepository.deleteFavorite(movie)
    }

    suspend fun getFavoriteByPosterPath(poster: String): Favorite {
        return favoriteRepository.getFavoriteByPosterPath(poster)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    data class FavoriteUiState(val posterList: List<String> = listOf())
    val favoriteUiState: StateFlow<FavoriteUiState> =
        favoriteRepository.getAllPostersStream().map { FavoriteUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavoriteUiState()
            )
}