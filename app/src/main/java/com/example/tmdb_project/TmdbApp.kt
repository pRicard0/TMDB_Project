package com.example.tmdb_project

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tmdb_project.ui.theme.screens.Home.HomeScreen
@Composable
fun TmdbApp(navController: NavHostController = rememberNavController()) {
    HomeScreen(navController = navController)
}