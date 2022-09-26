package com.carvalho.thumbinator.feature.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carvalho.thumbinator.core.ui.components.BaseOutlinedButton
import com.carvalho.thumbinator.feature.home.view_model.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: HomeViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        BaseOutlinedButton(label = "logout") {
            viewModel.logout()
        }
    }
}