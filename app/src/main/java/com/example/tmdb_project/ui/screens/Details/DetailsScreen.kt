package com.example.tmdb_project.ui.screens.Home


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb_project.componentes.ButtonTrailer
import com.example.tmdb_project.navigation.NavigationDestination
import com.example.tmdb_project.ui.theme.screens.Home.UiState

object DetailsDestination : NavigationDestination {
    override val route = "movie_details"
    override val title = "Detalhes sobre o filme"
    const val movieIdArg = "movieId"
    val routeWithArgs = "$route/{$movieIdArg}"
}

@Composable
fun DetailsScreen(movieId: Int, navController: NavHostController) {
    val viewModel: DetailsViewModel = viewModel()
    LaunchedEffect(movieId) {
        Log.d("DetailsScreen", "Fetching details for movieId: $movieId")
        viewModel.getMovieDetails(movieId)
    }
    val movieDetails = viewModel.movieDetails

    when (viewModel.uiState) {
        is DetailsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DetailsUiState.Success -> {
            movieDetails?.let {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding()
                ) {
                    // Background Image
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w500${it.backdrop_path}")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Movie Backdrop",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.White
                        )
                        Text(
                            text = "Overview: ${it.overview}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ButtonTrailer(onClick = { /* TODO: Handle button 1 click */ })
                        }
                    }
                }
            }
        }
        is DetailsUiState.Error -> {
            // ... (Error state handling remains the same)
            Column {
                Text(text = "Erro!")
                Text(text = (viewModel.uiState as DetailsUiState.Error).message)
            }
        }

        else -> {Text(text = "No movie data available")}
    }
}