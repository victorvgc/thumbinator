package com.carvalho.thumbinator.core.current_user.domain.data_source

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import kotlinx.coroutines.flow.Flow

class CurrentUserDataSource {
    interface Remote {
        suspend fun getCurrentUser(): Response<CurrentUser>
        suspend fun observeSession(): Flow<Response<CurrentUser>>
    }
}