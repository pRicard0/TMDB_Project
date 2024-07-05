package com.example.tmdb_project.ui.theme.screens.Home

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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import com.example.tmdb_project.navigation.NavigationDestination
import com.example.tmdb_project.ui.AppViewModelProvider
import com.example.tmdb_project.ui.theme.BackgroundGreyColor
import kotlinx.coroutines.launch

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val title = "Home Screen"
}

@Composable
fun HomeScreen(
viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
navController: NavController,
onHomeIconClick: () -> Unit,
onFavoriteIconClick: () -> Unit
) {
    val listAllTopMovies = viewModel.listAllMovies
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                onNavigationIconClick = {
                    scope.launch { drawerState.open() }
                },
                onHomeIconClick = onHomeIconClick,
                onFavoriteIconClick = onFavoriteIconClick
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).background(BackgroundGreyColor)
        ) {
            when (viewModel.uiState) {
                is UiState.Loading -> {
                    LoadingScreen()
                }

                is UiState.Success -> {
                    listAllTopMovies?.let {
                        AllCards(
                            listAll = it,
                            navController = navController,
                            viewModel = viewModel
                        )
                    }
                }

                is UiState.Error -> {
                    Text(text = (viewModel.uiState as UiState.Error).message)
                }
            }
        }
    }
}


@Composable
fun AllCards(listAll: Array<CardResponse>, viewModel: HomeViewModel, navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(135.dp),
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listAll.size) { item ->
            MovieCard(
                listAll = listAll,
                item = item,
                // Favoritando os filmes, enviando para o banco de dados. TODO, apagar e implementar na tela de detalhes
                onDetailsClick = {
                    navController.navigate("movie_details/${listAll[item].id}")
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
