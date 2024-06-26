package com.example.tmdb_project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tmdb_project.TmdbApplication
import com.example.tmdb_project.data.TmdbRepository
import com.example.tmdb_project.model.MovieImagesResponse
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface TmdbApiStatus {
    data class Success(val imagesResponse: MovieImagesResponse) : TmdbApiStatus
    object Loading : TmdbApiStatus
    object Error : TmdbApiStatus
}

class TmdbViewModel(private val tmdbRepository: TmdbRepository): ViewModel() {
//    private val _status = MutableStateFlow<TmdbApiStatus>(TmdbApiStatus.Loading)
//    val status: StateFlow<TmdbApiStatus> = _status.asStateFlow()
    var tmdbApiStatus: TmdbApiStatus by mutableStateOf(TmdbApiStatus.Loading)
        private set

    init {
        getMovieImage()
    }

    fun getMovieImage() {
        viewModelScope.launch {
            tmdbApiStatus = TmdbApiStatus.Loading
            tmdbApiStatus = try {
                TmdbApiStatus.Success(tmdbRepository.getMovieImage(movieId = 550))
            } catch (e: IOException) {
                TmdbApiStatus.Error
            } catch (e: HttpException) {
                TmdbApiStatus.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TmdbApplication)
                val tmdbRepository = application.container.tmdbRepository
                TmdbViewModel(tmdbRepository = tmdbRepository)
            }
        }
    }
}