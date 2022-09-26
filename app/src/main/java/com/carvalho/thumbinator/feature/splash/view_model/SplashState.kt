package com.carvalho.thumbinator.feature.splash.view_model

sealed class SplashState {
    object LoggedIn: SplashState()
}

sealed class SplashFailure {
    object LoggedOut: SplashFailure()
}