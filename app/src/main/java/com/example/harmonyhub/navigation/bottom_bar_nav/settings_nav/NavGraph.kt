package com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.screens.PlaylistScreen
import com.example.harmonyhub.features.settings.presentation.screens.SettingsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen



fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    authViewModel: AuthViewModel,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    navigation<BottomNavRoutes.Settings>(
        startDestination = SettingsNavRoutes.SettingsScreen
    ){
        composable<SettingsNavRoutes.SettingsScreen> {
            SettingsScreen(paddingValues,navController, authViewModel= authViewModel)
        }
        composable<PlaylistDetailScreen>{
            val data=it.toRoute<PlaylistDetailScreen>()
            PlaylistScreen(navController, data, musicPlayerViewModel)
        }

    }
}