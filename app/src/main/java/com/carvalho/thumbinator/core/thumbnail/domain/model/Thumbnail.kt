package com.carvalho.thumbinator.core.thumbnail.domain.model

import java.util.*
import kotlin.time.Duration

data class Thumbnail(
    val id: String = "",
    val title: String,
    val observation: String,
    val createdAt: Date,
    val executionDuration: Duration,
    val imageList: List<ThumbImage>,
    var tagList: List<ThumbTag>
) {
    val sortedThumbList = imageList.sortedBy { it.approved }.asReversed()
}