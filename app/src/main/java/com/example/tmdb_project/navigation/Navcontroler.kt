package com.example.tmdb_project.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tmdb_project.componentes.TopBar
import kotlinx.coroutines.launch


@Composable
fun NavControler2() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column {
                TextButton(onClick = { navController.navigate("home") }) {
                    Text(text = "Filmes", color = Color.White)
                }
                TextButton(onClick = { navController.navigate("series") }) {
                    Text(text = "Séries", color = Color.White)
                }
                TextButton(onClick = { navController.navigate("favorites") }) {
                    Text(text = "Favoritos", color = Color.White)
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onNavigationIconClick = {
                        scope.launch { drawerState.open() }
                    },
                    onHomeIconClick = {
                        navController.navigate("home")
                    },
                    onFavoriteIconClick = {
                        navController.navigate("favorites")
                    }
                )
            }
        ) { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .background(Color.DarkGray)
            ) {
                NavHost(navController, startDestination = "home") {
                    composable("home") { /* Seu conteúdo da home */ }
                    composable("favorites") { /* Seu conteúdo dos favoritos */ }
                    composable("details/{movieId}") { backstackEntry ->
                        backstackEntry.arguments?.getString("movieId")?.toIntOrNull()?.let { movieId ->

                        }
                    }
                }
            }
        }
    }
}