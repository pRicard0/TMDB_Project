package com.example.tmdb_project.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tmdb_project.data.network.response.CardResponse

@Composable
fun MovieCard(listAll: Array<CardResponse>, item: Int) {
    Card(
        Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { /*TODO*/ }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data("https://image.tmdb.org/t/p/w500${listAll[item].poster_path}")
                .crossfade(true)
                .build(),
            contentDescription = "CardFilms",
            contentScale = ContentScale.Fit
        )
    }
}
