package com.carvalho.thumbinator.core.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoadingScreen(progress: Float = -1f) {
    Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                strokeWidth = 8.dp
            )
            if (progress != -1f) {
                Text(text = "$progress%", style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}