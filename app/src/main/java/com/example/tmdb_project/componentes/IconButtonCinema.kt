package com.example.tmdb_project.componentes

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonCinema (image: ImageVector, description: String="", color: Color=White, onClick:()->Unit){
    IconButton(onClick = { onClick()} ) {
        Icon(image, contentDescription = description, tint = color, modifier = Modifier.size(34.dp))
    }
}