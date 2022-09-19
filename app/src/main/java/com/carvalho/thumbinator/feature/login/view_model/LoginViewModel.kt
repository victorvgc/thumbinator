package com.carvalho.thumbinator.feature.login.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.feature.login.domain.model.LoginUser
import com.carvalho.thumbinator.feature.login.domain.use_case.DoLoginUseCase
import com.carvalho.thumbinator.feature.login.domain.use_case.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val doLoginUserCase: DoLoginUseCase,
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    var uiState = mutableStateOf<BaseState<LoginFailure, LoginSuccess>>(BaseState.Loading())

    init {
        uiState.value = BaseState.Empty()
    }

    val userState = mutableStateOf("")
    val pwdState = mutableStateOf("")

    fun doLogin(user: String, password: String) {
        if (validateLoginForm(user, password)) {
            viewModelScope.launch {
                doLoginUserCase(
                    LoginUser(
                        username = user,
                        password = password
                    )
                ).collectLatest { state ->
                    uiState.value = state
                }
            }
        }
    }

    fun registerUser(user: String, password: String) {
        if (validateLoginForm(user, password)) {
            viewModelScope.launch {
                registerUserUseCase(
                    LoginUser(
                        username = user.trim(),
                        password = password.trim()
                    )
                ).collectLatest { state ->
                    uiState.value = state
                }
            }
        }
    }

    private fun validateLoginForm(user: String, password: String): Boolean {
        return if (user.isEmpty() || password.isEmpty()) {
            val msg = "must not be empty"
            uiState.value = BaseState.Failure(
                msg,
                LoginFailure(
                    username = if (user.isEmpty()) "Username $msg" else "",
                    password = if (password.isEmpty()) "Password $msg" else ""
                )
            )

            false
        } else true
    }
}