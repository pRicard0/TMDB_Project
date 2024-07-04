package com.example.tmdb_project.ui.theme.screens.Home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tmdb_project.R
import com.example.tmdb_project.componentes.MovieCard
import com.example.tmdb_project.componentes.favoritesScreen.FavoritedCard
import com.example.tmdb_project.data.network.response.CardResponse
import com.example.tmdb_project.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun TopRatedMovies(
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val listAllTopMovies = viewModel.listAllMovies
    val favoriteUiState by viewModel.favoriteUiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        when (viewModel.uiState) {
            is UiState.Loading -> {
                LoadingScreen()
            }
            is UiState.Success -> {
                (viewModel.uiState as UiState.Success).movies?.let {
                    if (listAllTopMovies != null) {
                        //AllCards(listAll = listAllTopMovies, viewModel = viewModel)
                        Text(
                            text = "Favorites",
                            fontSize = 32.sp,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(vertical = 16.dp)
                        )
                        FavoritesBody(
                            posterList = favoriteUiState.posterList,
                            viewModel = viewModel
                        )
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
fun AllCards(listAll: Array<CardResponse>, viewModel: HomeViewModel){
    val coroutineScope = rememberCoroutineScope()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(135.dp),
        modifier = Modifier.background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ) {
        items(listAll.size) { item ->
            MovieCard(
                listAll = listAll,
                item = item,
                // Favoritando os filmes, enviando para o banco de dados. TODO, apagar e implementar na tela de detalhes
                onFavoriteClick = {
                    coroutineScope.launch {
                        viewModel.favoriteMovie(listAll[item])
                    }
                }
            )
        }
    }
}

@Composable
fun FavoritesBody(
    posterList: List<String>,
    viewModel: HomeViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(135.dp),
        modifier = Modifier.background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center,
    ) {
        items(posterList.size) { posterIndex ->
            FavoritedCard(
                posterList = posterList,
                item = posterIndex,
                onUnfavoriteClick = {
                    coroutineScope.launch {
                        viewModel.unfavoriteMovie(viewModel.getFavoriteByPosterPath(posterList[posterIndex]))
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