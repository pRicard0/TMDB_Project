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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavController
import com.example.tmdb_project.data.network.response.MediaType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onHomeIconClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
    title: String = "App Title",
    actions: @Composable RowScope.() -> Unit = {},
    navController: NavController,
    onNavigationIconClick: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Color.Black
            ) { // Corrected background color setting
                LazyColumn {
                    item {
                        NavigationDrawerItem(
                            label = { Text("Home", color = Color.White) },
                            selected = false,
                            onClick = {
                                navController.navigate("home_screen")
                                scope.launch { drawerState.close() }
                            },
                            icon = { Icon(Icons.Filled.Home, contentDescription = null, tint = Color.White) }
                        )
                    }
                    item {
                        NavigationDrawerItem(
                            label = { Text("Favorites", color = Color.White) },
                            selected = false,
                            onClick = {
                                navController.navigate("favorite_screen")
                                scope.launch { drawerState.close() }
                            },
                            icon = { Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.White) }
                        )
                    }
                    item {
                        NavigationDrawerItem(
                            label = { Text("Series", color = Color.White) },
                            selected = false,
                            onClick = {
                                navController.navigate("series_screen")
                                scope.launch { drawerState.close() }
                            },
                        )
                    }
                }
            }
        }
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White,
            ),
            title = {
                IconButton(onClick = onHomeIconClick) {
                    Icon(Icons.Filled.Home, contentDescription = "Home", tint = Color.White)
                }
            },
            navigationIcon = {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                }
            },
            actions = {
                IconButton(onClick = onFavoriteIconClick) {
                    Icon(Icons.Filled.Favorite, contentDescription = "Favorite", tint = Color.Red)
                }
                actions()
            },
        )
    }
}

