package com.carvalho.thumbinator.core.thumbnail.domain.model

import androidx.compose.ui.graphics.Color

enum class TagColor(val background: Color, val text: Color) {
    RED(Color.Red, Color.Black),
    BLUE(Color.Blue, Color.Black),
    YELLOW(Color.Yellow, Color.Black),
    GREEN(Color.Green, Color.Black),
    MAGENTA(Color.Magenta, Color.Black),
    CYAN(Color.Cyan, Color.Black),
    BLACK(Color.Black, Color.Black),
    WHITE(Color.White, Color.Black)
}
