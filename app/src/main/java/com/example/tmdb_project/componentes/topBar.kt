package com.example.tmdb_project.componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    icon = { Icon(Icons.Filled.Home, null) },
                    label = { Text("Home") },
                    selected = false, // Lógica de seleção aqui
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Navegar para a tela Home
                    }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Filled.Star, null) },
                    label = { Text("Filmes") },
                    selected = false, // Lógica de seleção aqui
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Navegar para a tela Filmes
                    }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Filled.ThumbUp, null) },
                    label = { Text("Séries") },
                    selected = false, // Lógica de seleção aqui
                    onClick = {
                        scope.launch { drawerState.close() }
                        // Navegar para a tela Séries
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = "Ícone do App",
                                tint = Color.Red,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO: Ação do botão */ }) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Ícone Favoritos",
                                tint = Color.Red
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppNavigationDrawerPreview() {
    AppNavigationDrawer()
}
