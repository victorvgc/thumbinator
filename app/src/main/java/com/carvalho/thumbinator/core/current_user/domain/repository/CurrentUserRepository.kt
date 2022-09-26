package com.carvalho.thumbinator.core.current_user.domain.repository

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import kotlinx.coroutines.flow.Flow

interface CurrentUserRepository {
    suspend fun getCurrentUser(): Response<CurrentUser>

    suspend fun observeCurrentSession(): Flow<Response<CurrentUser>>

    suspend fun logout()
}