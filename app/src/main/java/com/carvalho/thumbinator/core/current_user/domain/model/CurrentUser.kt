package com.carvalho.thumbinator.core.current_user.domain.model

data class CurrentUser(
    val name: String,
    val email: String,
    val photoUrl: String?,
    val uid: String
)
