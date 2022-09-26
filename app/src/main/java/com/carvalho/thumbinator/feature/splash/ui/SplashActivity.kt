package com.carvalho.thumbinator.feature.splash.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.ui.components.LogoHolder
import com.carvalho.thumbinator.core.ui.theme.ThumbinatorTheme
import com.carvalho.thumbinator.feature.home.ui.HomeActivity
import com.carvalho.thumbinator.feature.login.ui.LoginActivity
import com.carvalho.thumbinator.feature.splash.view_model.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.util.*
import kotlin.random.Random

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThumbinatorTheme {
                val state = remember {
                    viewModel.uiState
                }

                when (state.value) {
                    is BaseState.Loading -> {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxHeight(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    LogoHolder(generateBgColor())
                                }

                            }
                        }
                    }
                    is BaseState.Success -> {
                        val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    else -> {
                        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }


            }
        }

        lifecycleScope.launchWhenCreated {
            delay(3000)

            viewModel.loadUser()
        }
    }

    private fun generateBgColor(): Color {
        val red = Random(Date().time).nextFloat()
        val green = Random(Date().time).nextFloat()
        val blue = Random(Date().time).nextFloat()

        return Color(red = red, green = green, blue = blue)
    }
}