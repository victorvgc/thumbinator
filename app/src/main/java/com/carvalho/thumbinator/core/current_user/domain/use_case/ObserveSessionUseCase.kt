package com.carvalho.thumbinator.core.current_user.domain.use_case

import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.core.current_user.domain.model.SessionFailure
import com.carvalho.thumbinator.core.current_user.domain.repository.CurrentUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObserveSessionUseCase @Inject constructor(private val userRepository: CurrentUserRepository) {

    suspend operator fun invoke(): Flow<BaseState<SessionFailure, CurrentUser>> =
        userRepository.observeCurrentSession().map {
            if (it.data != null) {
                return@map BaseState.Success(it.data)
            } else {
                return@map BaseState.Failure(it.errorMsg!!, SessionFailure.UserNotFound)
            }
        }
}