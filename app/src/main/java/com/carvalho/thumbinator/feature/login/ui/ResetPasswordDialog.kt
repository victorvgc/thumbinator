package com.carvalho.thumbinator.feature.login.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.carvalho.thumbinator.core.ui.components.BaseDialog
import com.carvalho.thumbinator.core.ui.components.BaseTextFiled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordDialog(
    onDismiss: () -> Unit,
    onConfirm: (resetUsername: String) -> Unit
) {
    val email = remember {
        mutableStateOf("")
    }
    val resetDialogError = remember {
        mutableStateOf("")
    }

    BaseDialog(
        title = "Reset your password",
        positiveButtonText = "Reset",
        negativeButtonText = "Cancel",
        onNegativeClick = onDismiss,
        onPositiveClick = {
            if (email.value.trim().isNotEmpty()) {
                onConfirm(email.value)
            } else {
                resetDialogError.value = "Email must not be empty"
            }
        },
        onDismissRequest = onDismiss
    ) {
        BaseTextFiled(
            value = email,
            errorText = resetDialogError.value,
            label = "Account email",
            preRenderErrorText = true,
            onTextChanged = {
                email.value = it
            })
    }

/*
    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(text = "Email to reset your password")
        },
        text = {
            BaseTextFiled(
                value = email,
                errorText = resetDialogError.value,
                label = resetDialogError.value.ifEmpty { "Account email" },
                onTextChanged = {
                    email.value = it
                })
        },
        confirmButton = {
            BaseFilledButton(label = "Send reset email") {
                if (email.value.trim().isNotEmpty()) {
                    onConfirm(email.value)
                } else {
                    resetDialogError.value = "Email must not be empty"
                }
            }
        },
        dismissButton = {
            BaseTextButton(label = "Cancel") {
                onDismiss()
            }
        }
    )*/
}