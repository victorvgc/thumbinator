package com.carvalho.thumbinator.feature.home.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.carvalho.thumbinator.core.arch.state.BaseState
import com.carvalho.thumbinator.core.ui.screens.LoadingScreen
import com.carvalho.thumbinator.core.ui.theme.ThumbinatorTheme
import com.carvalho.thumbinator.feature.home.view_model.HomeFailure
import com.carvalho.thumbinator.feature.home.view_model.HomeState
import com.carvalho.thumbinator.feature.home.view_model.HomeViewModel
import com.carvalho.thumbinator.feature.login.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThumbinatorTheme {
                val state = remember {
                    viewModel.uiState
                }

                when (val current = state.value) {
                    is BaseState.Empty -> {
                        StubScreen(text = "Empty data")
                    }
                    is BaseState.Loading -> {
                        when (current.data) {
                            is HomeState.LoadingProgress -> LoadingScreen(current.data.progress)
                            else -> LoadingScreen(100f)
                        }
                    }
                    is BaseState.Success -> {
                        MainScreen(viewModel)
                    }
                    is BaseState.Failure -> {
                        when (current.error) {
                            HomeFailure.FailedToLoadState -> MainScreen(viewModel = viewModel)
                            HomeFailure.Logout -> {
                                Intent(this, LoginActivity::class.java).apply {
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: HomeViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Statistics,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.route == screen.route

    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                style = TextStyle(
                    color = if (selected) Color.White else Color.DarkGray
                )
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = screen.title,
                tint = if (selected) Color.White else Color.DarkGray
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = Color.White,
        unselectedContentColor = Color.DarkGray,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}