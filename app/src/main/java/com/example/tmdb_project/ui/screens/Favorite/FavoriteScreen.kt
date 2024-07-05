package com.example.tmdb_project.ui.screens.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tmdb_project.componentes.TopBar
import com.example.tmdb_project.componentes.favoritesScreen.FavoritedCard
import com.example.tmdb_project.navigation.NavigationDestination
import com.example.tmdb_project.ui.AppViewModelProvider
import com.example.tmdb_project.ui.screens.Favorite.FavoriteViewModel
import com.example.tmdb_project.ui.theme.BackgroundGreyColor
import com.example.tmdb_project.ui.theme.TMDB_ProjectTheme
import com.example.tmdb_project.ui.theme.Typography
import com.example.tmdb_project.ui.theme.screens.Home.HomeViewModel
import kotlinx.coroutines.launch

object FavoriteDestination : NavigationDestination {
    override val route = "favoritos"
    override val title = "Minha lista de favoritos"
}

@Composable
fun FavoriteScreen(
    navController: NavController,
    onHomeIconClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
    viewModel: FavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val favoriteUiState by viewModel.favoriteUiState.collectAsState()
    Scaffold(
        topBar = {
            TopBar(
                onNavigationIconClick = { /*TODO*/ },
                onHomeIconClick = onHomeIconClick,
                onFavoriteIconClick = onFavoriteIconClick
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.background(BackgroundGreyColor)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Minha Lista",
                    style = Typography.titleLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                FavoritesBody(posterList = favoriteUiState.posterList, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun FavoritesBody(
    posterList: List<String>,
    viewModel: FavoriteViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(135.dp),
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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

@Preview
@Composable
fun FavoritePreview() {
    TMDB_ProjectTheme {
    }
}
