package com.carvalho.thumbinator.feature.thumb_list.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.ui.theme.ThumbinatorTheme
import com.carvalho.thumbinator.feature.thumb_list.view_model.ThumbListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThumbListActivity : ComponentActivity() {

    private val viewModel: ThumbListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThumbinatorTheme() {
                Surface() {
                    val uiState = remember {
                        viewModel.uiState
                    }

                    when (val currentState = uiState.value) {
                        is BaseState.Empty -> {
                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "empty".uppercase(),
                                        style = TextStyle(fontSize = 18.sp)
                                    )
                                }
                            }
                        }
                        is BaseState.Failure -> {
                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = currentState.message.uppercase(),
                                        style = TextStyle(fontSize = 18.sp)
                                    )
                                }
                            }
                        }
                        is BaseState.Loading -> {
                            Row(
                                modifier = Modifier.fillMaxHeight(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator(
                                        strokeWidth = 8.dp
                                    )
                                }
                            }
                        }
                        is BaseState.Success -> {
                            ThumbListScreen(thumbList = currentState.data) {
                                Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}