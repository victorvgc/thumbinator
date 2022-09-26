package com.carvalho.thumbinator.core.current_user.domain.use_case

import com.carvalho.thumbinator.core.current_user.domain.repository.CurrentUserRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val currentUserRepository: CurrentUserRepository) {

    suspend operator fun invoke() {
        currentUserRepository.logout()
    }
}