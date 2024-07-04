package com.example.tmdb_project.ui.theme.screens.Home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_project.data.database.Favorite
import com.example.tmdb_project.data.database.FavoriteRepository
import com.example.tmdb_project.data.network.response.CardResponse
import com.example.tmdb_project.data.network.service.ApiService
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class UiState {
    object Loading : UiState()
    data class Success(val movies: Array<CardResponse>?) : UiState()
    data class Error(val message: String) : UiState()
}

class HomeViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    var listAllMovies by mutableStateOf<Array<CardResponse>?>(null)
        private set

    var uiState by mutableStateOf<UiState>(UiState.Loading)
    private set

    private val tempMovies = mutableListOf<CardResponse>()
    var testAllMovies: String by mutableStateOf("")

    init {
        getTopRatedMovies()
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val response = ApiService.service.getTopRatedMovies()
                val limitArray = response.results.size-1
                for (i in 0 .. limitArray) {
                    tempMovies.add(response.results[i])
                }
                listAllMovies = tempMovies.toTypedArray()
                uiState = UiState.Success(listAllMovies)
            } catch (e: Exception) {
                testAllMovies = e.message.toString()
                uiState = UiState.Error(testAllMovies)
            }
        }
    }





    // Parte de banco de dados. TODO remover depois para implementar na viewModel de Favorites
    fun CardResponse.toFavorite(): Favorite = Favorite(
        id = id,
        title = title,
        posterPath = poster_path
    )
    suspend fun favoriteMovie(movie: CardResponse) {
        favoriteRepository.insertFavorite(movie.toFavorite())
    }

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
    ///////////////////////////////////////////////////////////
}