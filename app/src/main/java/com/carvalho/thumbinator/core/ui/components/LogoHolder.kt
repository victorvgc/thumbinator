package com.carvalho.thumbinator.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.carvalho.thumbinator.R

@Composable
fun LogoHolder() {
    Image(
        painter = painterResource(
            id = R.drawable.temp_logo
        ),
        contentDescription = "App logo",
    )
}