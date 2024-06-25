package com.example.tmdb_project.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SaveButton() {
    OutlinedButton(
        onClick = { /* TODO: Ação ao clicar no botão */ },
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black), // Texto branco
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite, // Ícone de coração
            contentDescription = "Salvar",
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(8.dp)) // Espaçamento entre o ícone e o texto
        Text("Salvar")
    }
}