package com.carvalho.thumbinator.core.current_user.data.data_source

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.data_source.CurrentUserDataSource
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RemoteCurrentUserDataSourceImpl @Inject constructor(private val auth: FirebaseAuth) :
    CurrentUserDataSource.Remote {
    override suspend fun getCurrentUser(): Response<CurrentUser> {
        return getCurrentUserFromAuth(auth.currentUser)
    }

    override suspend fun observeSession(): Flow<Response<CurrentUser>> = callbackFlow {
        auth.addAuthStateListener {
            trySend(getCurrentUserFromAuth(it.currentUser))
        }
    }


    private fun getCurrentUserFromAuth(user: FirebaseUser?): Response<CurrentUser> {
        return if (user != null) {
            Response(
                CurrentUser(
                    name = user.displayName ?: "No name",
                    email = user.email ?: "No email",
                    photoUrl = user.photoUrl?.toString() ?: "No photo",
                    uid = user.uid
                )
            )
        } else {
            Response(data = null, errorMsg = "User not found")
        }
    }
}