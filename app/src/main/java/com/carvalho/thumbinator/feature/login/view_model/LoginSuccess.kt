package com.carvalho.thumbinator.feature.login.view_model

import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser

data class LoginSuccess(
    val user: CurrentUser
)

data class LoginFailure(
    val username: String = "",
    val password: String = "",
    val validation: String = ""
)