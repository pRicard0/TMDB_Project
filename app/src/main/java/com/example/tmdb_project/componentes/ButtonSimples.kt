package com.example.tmdb_project.componentes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonTrailer(onClick: () -> Unit) {

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        )
    ) {

        Icon(
            imageVector = Icons.Filled.Favorite, // Ícone de coração
            contentDescription = "Salvar",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp)) // Espaçamento entre o ícone e o texto
        Text("Trailer")
    }
}