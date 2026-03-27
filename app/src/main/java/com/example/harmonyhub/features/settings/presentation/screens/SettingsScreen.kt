package com.example.harmonyhub.features.settings.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.about.presentation.components.AboutItem
import com.example.harmonyhub.features.app_update.presentation.components.AppUpdateItem
import com.example.harmonyhub.features.app_update.presentation.viewmodel.AppUpdateViewModel
import com.example.harmonyhub.features.settings.presentation.components.PreferredLanguageItem
import com.example.harmonyhub.features.settings.presentation.components.AudioQualityItem
import com.example.harmonyhub.features.settings.presentation.components.LogoutItem
import com.example.harmonyhub.features.storage.presentation.components.StorageInfoItem
import com.example.harmonyhub.features.settings.presentation.components.TopBar
import com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav.SettingsNavRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    appUpdateViewModel: AppUpdateViewModel,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar()
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            AudioQualityItem(
                authViewModel
            )

            PreferredLanguageItem(
                authViewModel
            )

            StorageInfoItem(
                navController
            )

            AppUpdateItem {
                appUpdateViewModel.checkForUpdates()
            }

            AboutItem {
                navController.navigate(SettingsNavRoutes.AboutScreen)
            }

            LogoutItem(
                authViewModel
            )

        }
    }
}
