package com.carvalho.thumbinator.feature.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.ui.screens.LoadingScreen
import com.carvalho.thumbinator.core.ui.theme.ThumbinatorTheme
import com.carvalho.thumbinator.feature.home.ui.HomeActivity
import com.carvalho.thumbinator.feature.home.ui.StubScreen
import com.carvalho.thumbinator.feature.login.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterial3Api
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThumbinatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = remember {
                        viewModel.uiState
                    }

                    when (val currentState = state.value) {
                        is BaseState.Empty -> {
                            LoginScreen(
                                isLoading = false,
                                errorState = null,
                                userTextMutableState = viewModel.userState,
                                pwdTextMutableState = viewModel.pwdState,
                                viewModel::doLogin,
                                viewModel::registerUser,
                                viewModel::resetPassword,
                                viewModel::cancelResetPassword
                            )
                        }
                        is BaseState.Failure -> {
                            LoginScreen(
                                isLoading = false,
                                errorState = currentState.error,
                                userTextMutableState = viewModel.userState,
                                pwdTextMutableState = viewModel.pwdState,
                                viewModel::doLogin,
                                viewModel::registerUser,
                                viewModel::resetPassword,
                                viewModel::cancelResetPassword
                            )
                        }
                        is BaseState.Loading -> {
                            LoginScreen(
                                isLoading = true,
                                errorState = null,
                                userTextMutableState = viewModel.userState,
                                pwdTextMutableState = viewModel.pwdState,
                                viewModel::doLogin,
                                viewModel::registerUser,
                                viewModel::resetPassword,
                                viewModel::cancelResetPassword
                            )
                        }
                        is BaseState.Success -> {
                            if (currentState.data.isReset) {
                                Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()
                                LoginScreen(
                                    isLoading = false,
                                    errorState = null,
                                    userTextMutableState = viewModel.userState,
                                    pwdTextMutableState = viewModel.pwdState,
                                    viewModel::doLogin,
                                    viewModel::registerUser,
                                    viewModel::resetPassword,
                                    viewModel::cancelResetPassword
                                )
                            } else {
                                Intent(this, HomeActivity::class.java).apply {
                                    startActivity(this)
                                    finish()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThumbinatorTheme {
        val user = remember {
            mutableStateOf("")
        }

        val pwd = remember {
            mutableStateOf("")
        }

        LoginScreen(false, null, user, pwd, { _, _ -> }, { _, _ -> }, {}, {})
    }
}
