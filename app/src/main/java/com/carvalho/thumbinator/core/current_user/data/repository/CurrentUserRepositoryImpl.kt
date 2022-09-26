package com.carvalho.thumbinator.core.current_user.data.repository

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.data_source.CurrentUserDataSource
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.current_user.domain.repository.CurrentUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrentUserRepositoryImpl @Inject constructor(private val remote: CurrentUserDataSource.Remote) :
    CurrentUserRepository {
    override suspend fun getCurrentUser(): Response<CurrentUser> {
        return remote.getCurrentUser()
    }

    override suspend fun observeCurrentSession(): Flow<Response<CurrentUser>> {
        return remote.observeSession()
    }
}