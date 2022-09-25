package com.carvalho.thumbinator.core.thumbnail.domain.model

data class ThumbTag(
    val name: String,
    val description: String = "",
    val color: TagColor,
)
