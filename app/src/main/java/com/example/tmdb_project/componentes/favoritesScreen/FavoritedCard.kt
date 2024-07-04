    package com.example.tmdb_project.componentes.favoritesScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb_project.R

@Composable
fun FavoritedCard(
    posterList: List<String>,
    item: Int,
    onUnfavoriteClick: () -> Unit,
) {
    Box {
        Card(
            Modifier
                .padding(12.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { /*TODO*/ }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500${posterList[item]}")
                    .crossfade(true)
                    .build(),
                contentDescription = "CardFilms",
                contentScale = ContentScale.Fit
            )
        }
        Box(
        modifier = Modifier
            .offset(y = 5.dp, x = 0.dp)
            .size(28.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(2.dp)
            .align(Alignment.TopEnd)
            .clickable { onUnfavoriteClick() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(Color.Red)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_close_24),
                    contentDescription = "Remove from favorites",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp).align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
fun FavoritedCardPreview() {
    Column(
        modifier = Modifier
             .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp)
    ) {

    }
}