package com.example.tmdb_project.componentes.detailsScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmdb_project.R
import com.example.tmdb_project.ui.theme.ButtonRedColor
import com.example.tmdb_project.ui.theme.Typography

@Composable
fun DetailsButton(color: Color, @DrawableRes icon: Int, text: String, onClick: () -> Unit) {
    var outlinedButtonColor: Color? = null
    if (color == Color.Transparent) {
        outlinedButtonColor = Color.White
    } else {
        outlinedButtonColor = color
    }
    Box(
        modifier = Modifier
            .height(42.dp)
            .width(108.dp)
            .border(1.dp, outlinedButtonColor, RoundedCornerShape(8.dp))
            .background(color, RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.height(20.dp).padding(end = 1.dp)
            )
            Text(
                text = text,
                color = Color.White,
                style = Typography.bodyLarge,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun DetailsButtonPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        DetailsButton(color = ButtonRedColor, icon = R.drawable.baseline_play_arrow_24, text = "Trailer", onClick = {})
        DetailsButton(color = Color.Transparent, icon = R.drawable.heart_svgrepo_com, text = "Salvar", onClick = {})
    }
}