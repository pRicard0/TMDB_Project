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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tmdb_project.R
import com.example.tmdb_project.componentes.MovieCard
import com.example.tmdb_project.data.network.response.CardResponse


@Composable
fun TopRatedMovies(){
    val viewModel = viewModel<HomeViewModel>()
    val listAllTopMovies = viewModel.listAllMovies
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        when (viewModel.uiState) {
            is UiState.Loading -> {
                LoadingScreen()
            }
            is UiState.Success -> {
                (viewModel.uiState as UiState.Success).movies?.let {
                    if (listAllTopMovies != null) {
                        AllCards(listAll = listAllTopMovies)
                    }
                }
            }
            is UiState.Error -> {
                Text(text = (viewModel.uiState as UiState.Error).message)
            }
        }
    }
}

@Composable
fun AllCards(listAll: Array<CardResponse>){
    val viewModel = viewModel<HomeViewModel>()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(135.dp),
        modifier = Modifier.background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ) {
        items(listAll.size) { item ->
            MovieCard(listAll = listAll, item = item)
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
