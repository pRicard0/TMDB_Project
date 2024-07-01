package com.example.tmdb_project.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdb_project.R
import com.example.tmdb_project.ui.theme.Typography
import com.example.tmdb_project.ui.theme.WarningColor

@Composable
fun WarnAlert(
    color: Color = WarningColor,
    text: String,
    showWarn: Boolean,
    onClose: () -> Unit
) {
    val showWarning: Boolean = showWarn
    if (showWarning) {
        Box(
            modifier = Modifier
                .width(272.dp)
                .height(66.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color)
                .padding(start = 12.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                        .align(Alignment.End)
                        .clickable { onClose() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Close the warning",
                        tint = Color.White
                    )
                }
                Text(
                    text = text,
                    color = Color.White,
                    style = Typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
fun WarnAlertPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        WarnAlert(text = "Adicionado à sua lista!", onClose = {}, showWarn = true)
    }
}