package com.example.tmdb_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tmdb_project.componentes.AppNavigationDrawer
import com.example.tmdb_project.componentes.WarnAlert
import com.example.tmdb_project.ui.theme.TMDB_ProjectTheme
import com.example.tmdb_project.ui.theme.screens.Home.AllCards
import com.example.tmdb_project.ui.theme.screens.Home.TopRatedMovies

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDB_ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TmdbApp()
                }
            }
        }
    }
}