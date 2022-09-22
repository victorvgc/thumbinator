package com.carvalho.thumbinator.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carvalho.thumbinator.R

@ExperimentalMaterial3Api
@Composable
fun BaseTextFiled(
    modifier: Modifier = Modifier,
    value: MutableState<String> = mutableStateOf(""),
    label: String = "",
    errorText: String? = null,
    isPassword: Boolean = false,
    maxLength: Int = -1,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    preRenderErrorText: Boolean = false,
    onTextChanged: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val textState = remember {
            value
        }
        val passwordVisible = remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            keyboardActions = keyboardActions,
            singleLine = true,
            modifier = modifier.fillMaxWidth(),
            value = textState.value,
            shape = RoundedCornerShape(8.dp),
            onValueChange = {
                if (maxLength != -1 && it.length > maxLength)
                    return@OutlinedTextField

                textState.value = it
                onTextChanged(it)
            },
            label = {
                if (label.isNotEmpty()) {
                    Text(text = label)
                }
            },
            visualTransformation = if (passwordVisible.value.not() && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            isError = errorText.isNullOrEmpty().not(),
            textStyle = TextStyle(fontSize = 16.sp),
            trailingIcon = {
                if (textState.value.isNotEmpty()) {
                    IconButton(
                        content = {
                            val painter = if (isPassword) {
                                if (passwordVisible.value) {
                                    painterResource(id = R.drawable.ic_not_visible)
                                } else {
                                    painterResource(id = R.drawable.ic_visible)
                                }
                            } else {
                                painterResource(id = R.drawable.ic_clear_field)
                            }

                            Icon(
                                painter = painter,
                                contentDescription = "Clear field"
                            )
                        },
                        onClick = {
                            if (isPassword) {
                                passwordVisible.value = passwordVisible.value.not()
                            } else {
                                textState.value = ""
                                onTextChanged("")
                            }
                        }
                    )
                } else if (errorText.isNullOrEmpty().not()) {
                    Icon(
                        painter = painterResource(R.drawable.ic_baseline_error_24),
                        contentDescription = errorText
                    )
                }
            }
        )

        if (errorText.isNullOrEmpty().not()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 0.dp),
                text = errorText ?: "Error",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Red,
                    textAlign = TextAlign.End
                )
            )
        } else if (preRenderErrorText) {
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}
