package com.carvalho.thumbinator.feature.login.domain.use_case

import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.feature.login.domain.model.LoginUser
import com.carvalho.thumbinator.feature.login.domain.repository.LoginRepository
import com.carvalho.thumbinator.feature.login.view_model.LoginFailure
import com.carvalho.thumbinator.feature.login.view_model.LoginSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(loginUser: LoginUser): Flow<BaseState<LoginFailure, LoginSuccess>> =
        flow {
            emit(BaseState.Loading())

            val result = loginRepository.resetPassword(loginUser)

            if (result.data == true) {
                emit(BaseState.Success(data = LoginSuccess(isReset = true)))
            } else {
                val msg = result.errorMsg ?: "Reset error"
                emit(BaseState.Failure(message = msg, error = LoginFailure(resetPassword = msg)))
            }
        }
}