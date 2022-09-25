package com.carvalho.thumbinator.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carvalho.thumbinator.feature.home.view_model.HomeViewModel
import com.carvalho.thumbinator.feature.thumb_list.ui.ThumbListScreen

@Composable
fun BottomNavGraph(viewModel: HomeViewModel, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            ThumbListScreen(thumbList = viewModel.thumbList, onClick = viewModel::onThumbClick)
        }
        composable(route = BottomBarScreen.Profile.route) {
            StubScreen(text = BottomBarScreen.Profile.title)
        }
        composable(route = BottomBarScreen.Statistics.route) {
            StubScreen(text = BottomBarScreen.Statistics.title)
        }
    }
}