package com.carvalho.thumbinator.feature.login.domain.use_case

import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.feature.login.domain.model.LoginUser
import com.carvalho.thumbinator.feature.login.domain.repository.LoginRepository
import com.carvalho.thumbinator.feature.login.view_model.LoginFailure
import com.carvalho.thumbinator.feature.login.view_model.LoginSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(loginUser: LoginUser): Flow<BaseState<LoginFailure, LoginSuccess>> =
        flow {
            emit(BaseState.Loading())

            val result = repository.registerUser(loginUser)

            if (result.errorMsg.isNullOrEmpty().not()) {
                emit(
                    BaseState.Failure(
                        message = result.errorMsg!!,
                        error = LoginFailure(validation = result.errorMsg)
                    )
                )
            } else {
                emit(BaseState.Success(LoginSuccess(result.data!!)))
            }
        }
}