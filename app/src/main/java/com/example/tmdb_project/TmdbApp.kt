package com.example.tmdb_project

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tmdb_project.navigation.TmdbNavHost

@Composable
fun TmdbApp(navController: NavHostController = rememberNavController()) {
    TmdbNavHost(navController = navController)
}