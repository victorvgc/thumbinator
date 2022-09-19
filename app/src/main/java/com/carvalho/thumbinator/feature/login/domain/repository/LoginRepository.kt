package com.carvalho.thumbinator.feature.login.domain.repository

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.feature.login.domain.model.LoginUser

interface LoginRepository {
    suspend fun doLogin(loginUser: LoginUser): Response<CurrentUser>

    suspend fun registerUser(loginUser: LoginUser): Response<CurrentUser>

    suspend fun resetPassword(loginUser: LoginUser): Response<Boolean>
}