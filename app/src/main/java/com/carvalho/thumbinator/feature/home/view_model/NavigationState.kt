package com.carvalho.thumbinator.feature.home.view_model

sealed class NavigationState {
    object Home: NavigationState()
    object Statistics: NavigationState()
    object Account: NavigationState()
}