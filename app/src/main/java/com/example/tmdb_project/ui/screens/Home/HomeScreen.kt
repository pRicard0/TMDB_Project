package com.example.tmdb_project.ui.theme.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tmdb_project.R
import com.example.tmdb_project.componentes.MovieCard
import com.example.tmdb_project.componentes.TopBar
import com.example.tmdb_project.data.network.response.CardResponse
import com.example.tmdb_project.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun  TopRatedMovies(
viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
navController: NavController // Add navController parameter
) {
    val listAllTopMovies = viewModel.listAllMovies

    Scaffold(
        topBar = {
            TopBar(
                onNavigationIconClick = { /*TODO*/ },
                onHomeIconClick = { /*TODO*/ },
                onFavoriteIconClick = { /*TODO*/ }
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            when (viewModel.uiState) {
                is UiState.Loading -> {
                    LoadingScreen()
                }

                is UiState.Success -> {
                    listAllTopMovies?.let { AllCards(listAll = it, navController = navController) }
                }

                is UiState.Error -> {
                    Text(text = (viewModel.uiState as UiState.Error).message)
                }
            }
        }
    }

    @Composable
    fun AllCards(listAll: Array<CardResponse>, navController: NavController) {
        val coroutineScope = rememberCoroutineScope()

        LazyVerticalGrid(
            columns = GridCells.Adaptive(135.dp),
            modifier = Modifier.background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listAll) { movie ->  // Iterate over listAll directly
                MovieCard(
                    listAll = listAll,
                    item = movie, // Pass the movie object directly
                    onFavoriteClick = {
                        coroutineScope.launch {
                            viewModel.favoriteMovie(movie) // Pass the movie object directly
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Image(
                modifier = modifier.size(312.dp),
                painter = painterResource(R.drawable.loading_img),
                contentDescription = "Loading"
            )
        }
    }
}
