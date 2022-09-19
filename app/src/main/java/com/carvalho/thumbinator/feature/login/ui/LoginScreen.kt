package com.carvalho.thumbinator.feature.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.carvalho.thumbinator.core.ui.components.*
import com.carvalho.thumbinator.feature.login.view_model.LoginFailure

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    isLoading: Boolean = false,
    errorState: LoginFailure?,
    userTextMutableState: MutableState<String>,
    pwdTextMutableState: MutableState<String>,
    doLogin: (user: String, pwd: String) -> Unit,
    registerUser: (user: String, pwd: String) -> Unit,
    resetPassword: (email: String) -> Unit,
    onResetPasswordDialogDismiss: () -> Unit
) {
    val (usernameField, passwordField) = FocusRequester.createRefs()

    Column(modifier = Modifier.padding(32.dp)) {
        val focusManager = LocalFocusManager.current

        val user = remember {
            userTextMutableState
        }
        val pwd = remember {
            pwdTextMutableState
        }

        val showResetDialog = remember {
            mutableStateOf(false)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            LogoHolder()
        }
        BaseTextFiled(
            value = userTextMutableState,
            modifier = Modifier
                .focusRequester(usernameField)
                .focusProperties { down = passwordField },
            keyboardActions = KeyboardActions(
                onPrevious = {
                    focusManager.clearFocus()
                },
                onDone = {
                    focusManager.moveFocus(FocusDirection.Down)
                },
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            label = "Username",
            onTextChanged = { user.value = it },
            isPassword = false,
            errorText = errorState?.username
        )
        Spacer(modifier = Modifier.padding(4.dp))
        BaseTextFiled(
            value = pwdTextMutableState,
            modifier = Modifier
                .focusRequester(passwordField)
                .focusProperties {
                    up = usernameField
                },
            keyboardActions = KeyboardActions(
                onPrevious = {
                    focusManager.moveFocus(FocusDirection.Up)
                },
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            maxLength = 8,
            label = "Password",
            isPassword = true,
            errorText = errorState?.password,
            onTextChanged = { pwd.value = it }
        )
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            BaseTextButton(label = "Forgot Password?") {
                showResetDialog.value = true
            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        if (isLoading) {
            DisplayLoading()
        } else {
            DisplayButtons(
                errorMsg = errorState?.validation,
                user = user.value,
                pwd = pwd.value,
                doLogin = doLogin,
                registerUser = registerUser
            )
        }

        if (showResetDialog.value ) {
            ResetPasswordDialog(
                onDismiss = {
                    showResetDialog.value = false
                    onResetPasswordDialogDismiss()
                },
                onConfirm = {
                    resetPassword(it)
                }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun DisplayButtons(
    errorMsg: String?,
    user: String,
    pwd: String,
    doLogin: (user: String, pwd: String) -> Unit,
    registerUser: (user: String, pwd: String) -> Unit
) {
    if (errorMsg.isNullOrEmpty().not()) {
        Text(text = errorMsg!!, style = TextStyle(color = Color.Red))
        Spacer(modifier = Modifier.padding(8.dp))
    }

    BaseFilledButton(label = "login") {
        doLogin(user, pwd)
    }
    Spacer(modifier = Modifier.padding(8.dp))
    BaseOutlinedButton(label = "register") {
        registerUser(user, pwd)
    }
}

@ExperimentalMaterial3Api
@Composable
private fun DisplayLoading() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            strokeWidth = 8.dp
        )
    }
}