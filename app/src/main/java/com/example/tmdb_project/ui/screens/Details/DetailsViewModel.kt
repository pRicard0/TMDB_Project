package com.example.tmdb_project.ui.screens.Home


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_project.data.database.Favorite
import com.example.tmdb_project.data.database.FavoriteRepository
import com.example.tmdb_project.data.network.response.CardResponse
import com.example.tmdb_project.data.network.response.MovieDetailsResponse
import com.example.tmdb_project.data.network.service.ApiService
import com.example.tmdb_project.ui.theme.screens.Home.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val movie: MovieDetailsResponse) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
}

class DetailsViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    var uiState by mutableStateOf<DetailsUiState>(DetailsUiState.Loading)
        private set

    var movieDetails by mutableStateOf<MovieDetailsResponse?>(null)
        private set

    var saveState = MutableStateFlow("Salvar")

    init {
        movieDetails?.let { processSaveState(movieDetails = it, viewModel = this) }
    }

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = ApiService.service.getMovieDetails(movieId)
                movieDetails = response
                uiState = DetailsUiState.Success(response)
            } catch (e: Exception) {
                uiState = DetailsUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun MovieDetailsResponse.toFavorite(): Favorite = Favorite(
        id = id,
        title = title,
        posterPath = poster_path
    )

    private suspend fun CardResponseToMovieDetailsResponse(cardResponse: CardResponse): MovieDetailsResponse {
        return ApiService.service.getMovieDetails(cardResponse.id)
    }

    suspend fun favoriteMovie(movie: MovieDetailsResponse) {
        favoriteRepository.insertFavorite(movie.toFavorite())
    }

    suspend fun getFavoriteById(id: Int): Int {
        return favoriteRepository.getFavoriteById(id)
    }

    suspend fun unfavoriteMovie(movie: Favorite) {
        favoriteRepository.deleteFavorite(movie)
    }

    suspend fun getSaveStateFromHome(movie: CardResponse) {
        val movie_details: MovieDetailsResponse = CardResponseToMovieDetailsResponse(movie)
        processSaveState(movieDetails = movie_details, viewModel = this)
    }

    fun processSaveState(movieDetails: MovieDetailsResponse, viewModel: DetailsViewModel) {
        viewModelScope.launch {
            val entity = viewModel.getFavoriteById(movieDetails.id)
            if (entity == movieDetails.id) {
                saveState.emit("Salvado")
                Log.d("DetailsScreen", "Mudando o estado do Salvamento para Salvado")
            } else {
                saveState.emit("Salvar")
                Log.d("DetailsScreen", "Erro ao tentar conferir")
            }
        }
    }

    fun processSaveSateByOnClick(movieDetails: MovieDetailsResponse, viewModel: DetailsViewModel) {
        viewModelScope.launch {
            val entity = viewModel.getFavoriteById(movieDetails.id)
            if (entity == movieDetails.id) {
                unfavoriteMovie(movieDetails.toFavorite())
                saveState.emit("Salvar")
            } else {
                favoriteMovie(movieDetails)
                saveState.emit("Salvado")
            }
        }
    }
}