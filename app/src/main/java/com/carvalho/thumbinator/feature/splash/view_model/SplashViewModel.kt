package com.carvalho.thumbinator.feature.splash.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.current_user.domain.use_case.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getCurrentUserUseCase: GetCurrentUserUseCase) :
    ViewModel() {

    private val _uiState =
        mutableStateOf<BaseState<SplashFailure, SplashState>>(BaseState.Loading())

    val uiState: State<BaseState<SplashFailure, SplashState>> = _uiState

    fun loadUser() {
        viewModelScope.launch {
            when (getCurrentUserUseCase()) {
                is BaseState.Loading -> _uiState.value = BaseState.Loading()
                is BaseState.Success -> _uiState.value = BaseState.Success(SplashState.LoggedIn)
                else -> _uiState.value =
                    BaseState.Failure("User not found", SplashFailure.LoggedOut)
            }
        }
    }
}