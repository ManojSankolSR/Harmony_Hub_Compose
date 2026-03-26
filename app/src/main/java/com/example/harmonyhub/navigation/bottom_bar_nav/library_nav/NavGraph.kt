package com.example.harmonyhub.navigation.bottom_bar_nav.library_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.harmonyhub.features.album.presentation.screens.AlbumScreen
import com.example.harmonyhub.features.library.presentation.screens.LibraryScreen
import com.example.harmonyhub.features.like.presentation.screens.LikedSongsScreen
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.local_palylist.presentation.screens.LocalPlaylistDetailsScreen
import com.example.harmonyhub.features.local_palylist.presentation.screens.LocalPlaylistScreen
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.screens.PlaylistScreen
import com.example.harmonyhub.features.song_download.presentation.screens.DownloadedSongsScreen
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel
import com.example.harmonyhub.navigation.bottom_bar_nav.AlbumDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen

fun NavGraphBuilder.libraryNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    musicPlayerViewModel: MusicPlayerViewModel,
    localPlaylistViewModel: LocalPlaylistViewModel,
    downloadsViewModel: DownloadsViewModel,
    likedSongsViewModel: LikedSongsViewModel,
) {
    navigation<BottomNavRoutes.Library>(
        startDestination = LibraryNavRoutes.LibraryScreen
    ) {
        composable<LibraryNavRoutes.LibraryScreen> {
            LibraryScreen(paddingValues, localPlaylistViewModel, navController)
        }
        composable<PlaylistDetailScreen> {
            val data = it.toRoute<PlaylistDetailScreen>()
            PlaylistScreen(
                paddingValues,
                navController,
                data,
                musicPlayerViewModel,
                downloadsViewModel,
                likedSongsViewModel
            )
        }
        composable<AlbumDetailsScreen> {
            val data = it.toRoute<AlbumDetailsScreen>()
            AlbumScreen(paddingValues, navController, data, musicPlayerViewModel,downloadsViewModel, likedSongsViewModel)
        }
        composable<LibraryNavRoutes.LocalPlaylists> {
            LocalPlaylistScreen(
                viewModel = localPlaylistViewModel,
                paddingValues = paddingValues,
                navController = navController
            )
        }
        composable<LibraryNavRoutes.LocalPlaylistDetails> {
            val data = it.toRoute<LibraryNavRoutes.LocalPlaylistDetails>()
            LocalPlaylistDetailsScreen(
                playlistId = data.id,
                playlistName = data.name,
                localPlaylistViewModel = localPlaylistViewModel,
                musicPlayerViewModel = musicPlayerViewModel,
                paddingValues = paddingValues,
                navController = navController
            )
        }
        composable<LibraryNavRoutes.DownloadedSongs> {
            DownloadedSongsScreen(
                viewModel = downloadsViewModel,
                musicPlayerViewModel = musicPlayerViewModel,
                onBackClick = { navController.popBackStack() },
                paddingValues
            )
        }
        composable<LibraryNavRoutes.LikedSongs> {
            LikedSongsScreen(
                viewModel = likedSongsViewModel,
                musicPlayerViewModel = musicPlayerViewModel,
                downloadsViewModel = downloadsViewModel,
                onBackClick = { navController.popBackStack() },
                paddingValues = paddingValues
            )
        }
    }
}