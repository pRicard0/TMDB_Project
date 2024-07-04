package com.example.tmdb_project.ui.screens.Home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb_project.data.network.response.MovieDetailsResponse
import com.example.tmdb_project.data.network.service.ApiService
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
    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = ApiService.service.getMovieDetails(movieId)
                _uiState.value = DetailsUiState.Success(response)
            } catch (e: Exception) {
                _uiState.value = DetailsUiState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}