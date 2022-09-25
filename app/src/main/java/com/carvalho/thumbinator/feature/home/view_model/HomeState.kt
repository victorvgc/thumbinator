package com.carvalho.thumbinator.feature.home.view_model

sealed class HomeState {
    data class LoadingProgress(val progress: Float = 0.0f): HomeState()
    object LoadedState: HomeState()
}

sealed class HomeFailure {
    object FailedToLoadState: HomeFailure()
}