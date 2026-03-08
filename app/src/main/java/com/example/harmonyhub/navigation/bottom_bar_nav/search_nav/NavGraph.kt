package com.example.harmonyhub.navigation.bottom_bar_nav.search_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.harmonyhub.features.album.presentation.screens.AlbumScreen
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.presentation.screens.PlaylistScreen
import com.example.harmonyhub.features.serach.presentation.screens.SearchScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.AlbumDetailsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen



fun NavGraphBuilder.searchNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    musicPlayerViewModel: MusicPlayerViewModel,

    ) {
    navigation<BottomNavRoutes.Search>(
        startDestination = SearchNavRoutes.SearchScreen
    ){
        composable<SearchNavRoutes.SearchScreen> {
            SearchScreen(paddingValues,navController)
        }
        composable<PlaylistDetailScreen>{
            val data=it.toRoute<PlaylistDetailScreen>()
            PlaylistScreen(navController,data,musicPlayerViewModel)
        }
        composable <AlbumDetailsScreen>{
            val data=it.toRoute<AlbumDetailsScreen>()
            AlbumScreen(navController, data, musicPlayerViewModel)
        }

    }
}