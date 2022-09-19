package com.carvalho.thumbinator.feature.login.data.data_source

import com.carvalho.thumbinator.core.arch.data_helpers.Response
import com.carvalho.thumbinator.core.current_user.domain.model.CurrentUser
import com.carvalho.thumbinator.feature.login.domain.data_source.LoginDataSource
import com.carvalho.thumbinator.feature.login.domain.model.LoginUser
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine

class LoginRemoteDataSourceImpl @Inject constructor(private val auth: FirebaseAuth) :
    LoginDataSource.Remote {
    override suspend fun signInWithEmail(loginUser: LoginUser): Response<CurrentUser> =
        suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(loginUser.username, loginUser.password)
                .addOnSuccessListener { result ->
                    continuation.resumeWith(Result.success(processAuthResult(result)))
                }
                .addOnFailureListener {
                    continuation.resumeWith(
                        Result.success(
                            Response(
                                errorMsg = it.message ?: "Login failed, code: 02"
                            )
                        )
                    )
                }
                .addOnCanceledListener {
                    continuation.resumeWith(Result.success(Response(errorMsg = "Login canceled, code: 03")))
                }
        }

    override suspend fun createUserWithEmail(loginUser: LoginUser): Response<CurrentUser> =
        suspendCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(loginUser.username, loginUser.password)
                .addOnSuccessListener { result ->
                    continuation.resumeWith(Result.success(processAuthResult(result)))
                }
                .addOnFailureListener {
                    continuation.resumeWith(
                        Result.success(
                            Response(
                                errorMsg = it.message ?: "Login failed, code: 02"
                            )
                        )
                    )
                }
                .addOnCanceledListener {
                    continuation.resumeWith(Result.success(Response(errorMsg = "Login canceled, code: 03")))
                }
        }

    private fun processAuthResult(result: AuthResult): Response<CurrentUser> {
        return result.user?.let {
            val currentUser = CurrentUser(
                name = it.displayName ?: it.email ?: "Error loading name",
                email = it.email ?: "Error loading email",
                photoUrl = it.photoUrl.toString(),
                uid = it.uid
            )

            Response(currentUser)
        } ?: run {
            Response(errorMsg = "Login failed, code: 01")
        }
    }
}