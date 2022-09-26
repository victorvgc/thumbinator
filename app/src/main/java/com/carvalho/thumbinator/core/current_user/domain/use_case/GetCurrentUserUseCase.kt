package com.carvalho.thumbinator.core.current_user.domain.use_case

import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.current_user.domain.model.SessionFailure
import com.carvalho.thumbinator.core.current_user.domain.repository.CurrentUserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val repository: CurrentUserRepository) {

    suspend operator fun invoke(): BaseState<SessionFailure, CurrentUser> {
        val response = repository.getCurrentUser()

        return if (response.data != null)
            BaseState.Success(response.data)
        else
            BaseState.Failure(response.errorMsg!!, SessionFailure.UserNotFound)
    }
}