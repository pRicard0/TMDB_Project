package com.example.tmdb_project.ui.screens.Home


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb_project.R
import com.example.tmdb_project.componentes.ButtonTrailer
import com.example.tmdb_project.componentes.TopBar
import com.example.tmdb_project.componentes.detailsScreen.DetailsButton
import com.example.tmdb_project.data.network.response.MovieDetailsResponse
import com.example.tmdb_project.navigation.NavigationDestination
import com.example.tmdb_project.ui.AppViewModelProvider
import com.example.tmdb_project.ui.theme.ButtonRedColor
import com.example.tmdb_project.ui.theme.Typography
import com.example.tmdb_project.ui.theme.screens.Home.HomeViewModel
import com.example.tmdb_project.ui.theme.screens.Home.UiState
import kotlinx.coroutines.launch

object DetailsDestination : NavigationDestination {
    override val route = "movie_details"
    override val title = "Detalhes sobre o filme"
    const val movieIdArg = "movieId"
    val routeWithArgs = "$route/{$movieIdArg}"
}

private fun validateReleaseDate(releaseDate: String): String {
    val year = releaseDate.substring(0, 4)
    return year
}

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    movieId: Int,
    navController: NavHostController,
    onHomeIconClick: () -> Unit,
    onFavoriteIconClick: () -> Unit
) {
    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }
    val movieDetails = viewModel.movieDetails
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                onNavigationIconClick = {
                    scope.launch { drawerState.open() }
                },
                onHomeIconClick = onHomeIconClick,
                onFavoriteIconClick = onFavoriteIconClick
            )
        }
    ) { innerPadding ->
        when (viewModel.uiState) {
            is DetailsUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is DetailsUiState.Success -> {
                DetailsBody(movieDetails = movieDetails, innerPadding = innerPadding, viewModel = viewModel)
            }
            is DetailsUiState.Error -> {
                Column {
                    Text(text = "Erro!")
                    Text(text = (viewModel.uiState as DetailsUiState.Error).message)
                }
            }

            else -> {Text(text = "No movie data available")}
        }
    }
}

@Composable
fun DetailsBody(
    movieDetails: MovieDetailsResponse?,
    innerPadding: PaddingValues,
    viewModel: DetailsViewModel
) {
    val saveState by viewModel.saveState.collectAsState() // Collect the state flow

    LaunchedEffect(key1 = viewModel.movieDetails) { // Trigger when movieDetails changes
        viewModel.movieDetails?.let {
            viewModel.processSaveState(it, viewModel)
        }
    }
    val coroutineScope = rememberCoroutineScope()
    movieDetails?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500${it.poster_path}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.7f))
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(
                    text = it.title,
                    style = Typography.titleLarge,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = validateReleaseDate(it.release_date),
                        style = Typography.labelMedium
                    )
                    Text(
                        text = it.vote_average.toString(),
                        style = Typography.labelLarge
                    )
                    Text(
                        text = "|",
                        style = Typography.labelMedium
                    )
                    Text(
                        text = it.runtime.toString(),
                        style = Typography.labelMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                it.tagline?.let { it1 ->
                    Text(
                        text = it1,
                        style = Typography.bodyLarge
                    )
                }
                Text(
                    text = it.overview,
                    style = Typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    DetailsButton(
                        color = ButtonRedColor,
                        icon = R.drawable.baseline_play_arrow_24,
                        text = "Trailer",
                        onClick = {/*TODO*/}
                    )
                    DetailsButton(
                        color = Color.Transparent,
                        icon = R.drawable.heart_svgrepo_com,
                        text = saveState,
                        onClick = {
                            coroutineScope.launch {
                                viewModel.processSaveSateByOnClick(it, viewModel)
                            }
                        }
                    )
                }
            }
        }
    }
}
