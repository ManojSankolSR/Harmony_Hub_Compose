package com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.harmonyhub.features.auth.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.album.presentation.screens.AlbumScreen
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.screens.PlaylistScreen
import com.example.harmonyhub.features.about.presentation.screens.AboutScreen
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.settings.presentation.screens.SettingsScreen
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel
import com.example.harmonyhub.features.storage.presentation.screens.StorageInfoScreen
import com.example.harmonyhub.features.storage.presentation.viewmodel.StorageViewModel
import com.example.harmonyhub.navigation.bottom_bar_nav.AlbumDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen



@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    authViewModel: AuthViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
    storageViewModel: StorageViewModel,
    downloadsViewModel: DownloadsViewModel,
    likedSongsViewModel: LikedSongsViewModel
) {
    navigation<BottomNavRoutes.Settings>(
        startDestination = SettingsNavRoutes.SettingsScreen
    ){
        composable<SettingsNavRoutes.SettingsScreen> {
            SettingsScreen(paddingValues,navController, authViewModel= authViewModel)
        }
        composable <SettingsNavRoutes.StorageInfoScreen>{
            StorageInfoScreen(storageViewModel,paddingValues,navController)
        }
        composable<SettingsNavRoutes.AboutScreen> {
            AboutScreen(paddingValues) {
                navController.popBackStack()
            }
        }
        composable<PlaylistDetailScreen>{
            val data=it.toRoute<PlaylistDetailScreen>()
            PlaylistScreen(
                paddingValues,
                navController,
                data,
                musicPlayerViewModel,
                downloadsViewModel,
                likedSongsViewModel
            )
        }
        composable <AlbumDetailsScreen>{
            val data=it.toRoute<AlbumDetailsScreen>()
            AlbumScreen(
                paddingValues,
                navController,
                data,
                musicPlayerViewModel,
                downloadsViewModel,
                likedSongsViewModel
            )
        }

    }
}