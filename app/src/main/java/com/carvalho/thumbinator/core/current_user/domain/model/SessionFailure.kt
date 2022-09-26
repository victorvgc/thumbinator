package com.carvalho.thumbinator.core.current_user.domain.model

sealed class SessionFailure {
    object UserNotFound: SessionFailure()
}