package com.example.harmonyhub.features.settings.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.settings.presentation.com.PreferredLanguageItem
import com.example.harmonyhub.features.settings.presentation.components.AudioQualityItem
import com.example.harmonyhub.features.settings.presentation.components.LogoutItem
import com.example.harmonyhub.ui.theme.PermanentMarker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    navController: NavHostController,
    authViewModel: AuthViewModel,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(
                    "Settings",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontFamily = PermanentMarker
                    )
                )
            })
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            AudioQualityItem(
                authViewModel
            )

            PreferredLanguageItem(
                authViewModel
            )

            LogoutItem(
                authViewModel
            )
        }
    }
}

