package com.example.tmdb_project.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White

import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    onNavigationIconClick: () -> Unit, // Callback for navigation icon click
    title: String = "App Title",
    actions: @Composable RowScope.() -> Unit = {} // Slot for custom actions
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = White,
        ),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = White)
            }
        },
        actions = actions,
        scrollBehavior = scrollBehavior,
    )
}

// Usage in your main screen composable (or wherever you need it)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { /* ... your drawer content */ }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch { drawerState.open() }
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
                    // ... your composable routes
                }
            }
        }
    }
}
                    /*composable("details/{movieId}"){ backstackEntry ->
                        backstackEntry.arguments?.getString("movieId")
                            ?.let { movieId ->
                                val viewModel = viewModel<DetailsViewModel>(
                                    factory = object :ViewModelProvider.Factory{
                                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                            return DetailsViewModel(movieId.toInt()) as T
                                        }
                                    }
                                )
                                DetailsScreen(viewModel.movieUiState)
                            }
                    }*/



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(scrollBehavior: TopAppBarScrollBehavior, scope: CoroutineScope, drawerState: DrawerState, navController: NavController) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = White,
        ),
        title = {
            IconButtonCinema(Icons.Filled.Home, "Home", White, { navController.navigate("home") })
        },
        navigationIcon = {
            IconButtonCinema(Icons.Filled.Menu, "Menu", White) {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }

            }
        },
        actions = {
            IconButtonCinema(Icons.Filled.Favorite, "Favorite", Red,{ navController.navigate("favorites") })
        },
        scrollBehavior = scrollBehavior,
    )

}

