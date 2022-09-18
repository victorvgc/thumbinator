package com.carvalho.thumbinator.feature.login.view_model

data class LoginState(
    val user: String,
    val password: String
)

data class LoginFailure(
    val username: Boolean = false,
    val password: Boolean = false,
    val validation: Boolean = false
)