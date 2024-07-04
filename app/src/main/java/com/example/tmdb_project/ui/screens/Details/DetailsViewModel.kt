package com.example.tmdb_project.ui.screens.Home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class DetailsViewModel : ViewModel() {
    var uiState by mutableStateOf<DetailsUiState>(DetailsUiState.Loading)
        private set

    var movieDetails by mutableStateOf<MovieDetailsResponse?>(null)
        private set


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
}