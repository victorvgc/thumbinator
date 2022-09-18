package com.carvalho.thumbinator.feature.login.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.carvalho.thumbinator.core.arch.state.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var uiState = mutableStateOf<BaseState<LoginFailure, LoginState>>(BaseState.Loading())

    init {
        uiState.value = BaseState.Empty()
    }

    fun doLogin(user: String, password: String) {
        uiState.value = if (user.isEmpty() || password.isEmpty()) {
            BaseState.Failure(
                "Fields must not be empty",
                LoginFailure(
                    username = user.isEmpty(),
                    password = password.isEmpty()
                )
            )
        } else {
            /** use case to login validation
             *  loginUseCase(user, pwd) { result ->
             *      if (result) {
             *          BaseState.Success(
             *              LoginState(
             *                  user = user,
             *                  password = password
             *                  )
             *              )
             *      } else {
             *          BaseState.Failure(
             *              "Username or password incorrect!",
             *              LoginFailure(validation = true)
             *          )
             *      }
             *  }
             */
            BaseState.Success(
                LoginState(
                    user = user,
                    password = password
                )
            )
        }
    }
}