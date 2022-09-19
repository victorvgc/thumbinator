package com.carvalho.thumbinator.feature.login.domain.data_source

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.feature.login.domain.model.LoginUser

interface LoginDataSource {
    interface Remote {
        suspend fun signInWithEmail(loginUser: LoginUser): Response<CurrentUser>

        suspend fun createUserWithEmail(loginUser: LoginUser): Response<CurrentUser>
    }
}