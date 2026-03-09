package com.example.harmonyhub.navigation.bottom_bar_nav.home_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.harmonyhub.features.album.presentation.screens.AlbumScreen
import com.example.harmonyhub.features.artist.presentation.screens.ArtistScreen
import com.example.harmonyhub.features.home.presentation.screens.HomeScreen
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.screens.PlaylistScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.AlbumDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.ArtistDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen



fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    musicPlayerViewModel: MusicPlayerViewModel,

    ) {
    navigation<BottomNavRoutes.Home>(
        startDestination = HomeNavRoutes.HomeScreen
    ){
        composable<HomeNavRoutes.HomeScreen> {
            HomeScreen(paddingValues,navController,musicPlayerViewModel)
        }
        composable <PlaylistDetailScreen>{
            val data=it.toRoute<PlaylistDetailScreen>()
            PlaylistScreen(paddingValues,navController, data, musicPlayerViewModel)
        }
        composable <AlbumDetailsScreen>{
            val data=it.toRoute<AlbumDetailsScreen>()
            AlbumScreen(paddingValues ,navController, data, musicPlayerViewModel)
        }

        composable <ArtistDetailsScreen>{
            val data=it.toRoute<ArtistDetailsScreen>()
            ArtistScreen(paddingValues,navController, data, musicPlayerViewModel)
        }
    }
}