package com.example.tmdb_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tmdb_project.ui.screens.Home.DetailsDestination
import com.example.tmdb_project.ui.screens.Home.DetailsScreen
import com.example.tmdb_project.ui.screens.Home.FavoriteDestination
import com.example.tmdb_project.ui.screens.Home.FavoriteScreen
import com.example.tmdb_project.ui.theme.screens.Home.HomeDestination
import com.example.tmdb_project.ui.theme.screens.Home.HomeScreen

@Composable
fun TmdbNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navController = navController,
                onHomeIconClick = { navController.navigate(HomeDestination.route) },
                onFavoriteIconClick = { navController.navigate(FavoriteDestination.route) }
            )
        }

        composable(route = FavoriteDestination.route) {
            FavoriteScreen(
                navController = navController,
                onHomeIconClick = { navController.navigate(HomeDestination.route) },
                onFavoriteIconClick = { navController.navigate(FavoriteDestination.route) }
            )
        }

        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.movieIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt(DetailsDestination.movieIdArg) ?: 0
            DetailsScreen(movieId = movieId, navController = navController)
        }
    }
}