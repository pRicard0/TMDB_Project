package com.example.tmdb_project.ui.screens.Home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.tmdb_project.componentes.TopBar

/*
@Composable
    fun FavoritesScreen(
        modifier:Modifier = Modifier,
        favoriteUiState: FavoriteUiState,
        navController: NavController,
        favoriteViewModel: FavoriteViewModel
    ) {

        var nextPage: Int by remember { mutableIntStateOf(2) }

    Scaffold(
        topBar = {
                TopBar(
                    onNavigationIconClick = { /*TODO*/ },
                    onHomeIconClick = { /*TODO*/ },
                    onFavoriteIconClick = { /*TODO*/ }
                )
        }

    ) { innerPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black),
        ) {

            LaunchedEffect(favoriteUiState) {
                if (favoriteUiState is FavoriteUiState.AddSuccess) {
                    Toast.makeText(
                        context,
                        "Filme adicionado aos favoritos!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

 */