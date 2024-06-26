package com.example.tmdb_project.ui.theme.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
                Text(text = "Loading")
            }
            is UiState.Success -> {
                (viewModel.uiState as UiState.Success).movies?.let { AllCards(listAll = it) }
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
        horizontalArrangement = Arrangement.Center
    ) {
        items(listAll.size) { item ->
            Card(
                Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { /*TODO*/ }
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${listAll[item].poster_path}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "CardFilms",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}