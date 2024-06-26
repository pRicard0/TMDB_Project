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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb_project.R

@Composable
fun FavoritedCard() {
    Box {
        Card(
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .clickable { /*TODO*/ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.f74fd251d32b1fec2592ecaaf8e87482),
                contentDescription = "teste",
            )
        }
        Box(
        modifier = Modifier
            .offset(y = (-10).dp, x = 10.dp)
            .size(28.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(2.dp)
            .align(Alignment.TopEnd)
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
        FavoritedCard()
    }
}