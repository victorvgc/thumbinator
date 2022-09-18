package com.carvalho.thumbinator.feature.login.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.ui.components.*
import com.carvalho.thumbinator.core.ui.theme.ThumbinatorTheme
import com.carvalho.thumbinator.feature.login.view_model.LoginFailure
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
                            LoginScreen(null, viewModel::doLogin)
                        }
                        is BaseState.Failure -> {
                            LoginScreen(currentState.error, viewModel::doLogin)
                        }
                        is BaseState.Loading -> {
                            LoadingScreen()
                        }
                        is BaseState.Success -> {
                            LoadingScreen("Login Successful")
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(errorState: LoginFailure?, doLogin: (user: String, pwd: String) -> Unit) {
    val errorMsg = "Field must not be empty!"
    Column(modifier = Modifier.padding(32.dp)) {
        val user = remember {
            mutableStateOf("")
        }
        val pwd = remember {
            mutableStateOf("")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            LogoHolder()
        }
        BaseTextFiled(
            label = "Username",
            onTextChanged = { user.value = it },
            isPassword = false,
            isError = errorState?.username == true,
            errorText = errorMsg
        )
        Spacer(modifier = Modifier.padding(4.dp))
        BaseTextFiled(
            label = "Password",
            isPassword = true,
            isError = errorState?.password == true,
            errorText = errorMsg,
            onTextChanged = { pwd.value = it }
        )
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            BaseTextButton(label = "Forgot Password?") {

            }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        BaseFilledButton(label = "login") {
            doLogin(user.value, pwd.value)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        BaseOutlinedButton(label = "register") {

        }
    }
}

@Composable
fun LoadingScreen(msg: String? = null) {
    Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            if (msg.isNullOrEmpty()) {
                CircularProgressIndicator(
                    strokeWidth = 8.dp
                )
            } else {
                Text(text = msg.uppercase(), style = TextStyle(fontSize = 18.sp))
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThumbinatorTheme {
        LoginScreen(null) { _, _ -> }
    }
}
