package com.carvalho.thumbinator.feature.login.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.carvalho.thumbinator.core.ui.components.BaseFilledButton
import com.carvalho.thumbinator.core.ui.components.BaseTextButton
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
    )
}