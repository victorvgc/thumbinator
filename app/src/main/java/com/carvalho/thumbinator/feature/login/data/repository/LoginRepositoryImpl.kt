package com.carvalho.thumbinator.feature.login.data.repository

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.feature.login.domain.data_source.LoginDataSource
import com.carvalho.thumbinator.feature.login.domain.model.LoginUser
import com.carvalho.thumbinator.feature.login.domain.repository.LoginRepository
import javax.inject.Inject
import javax.inject.Singleton

class LoginRepositoryImpl @Inject constructor(private val dataSource: LoginDataSource.Remote) : LoginRepository{
    override suspend fun doLogin(loginUser: LoginUser): Response<CurrentUser> {
        return dataSource.signInWithEmail(loginUser)
    }

    override suspend fun registerUser(loginUser: LoginUser): Response<CurrentUser> {
        return dataSource.createUserWithEmail(loginUser)
    }

}