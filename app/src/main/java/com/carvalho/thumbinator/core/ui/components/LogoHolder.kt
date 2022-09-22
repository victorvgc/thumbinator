package com.carvalho.thumbinator.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.carvalho.thumbinator.R

@Composable
fun LogoHolder(
    color: Color = MaterialTheme.colorScheme.primary
) {
    Image(
        painter = painterResource(
            id = R.drawable.temp_logo
        ),
        colorFilter = ColorFilter.tint(color),
        contentDescription = "App logo",
    )
}