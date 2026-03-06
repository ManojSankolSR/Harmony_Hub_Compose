package com.example.harmonyhub.navigation.bottom_bar_nav.home_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.harmonyhub.features.home.presentation.screens.HomeScreen
import com.example.harmonyhub.features.playlist.presentation.screens.PlaylistScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen



fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,

    ) {
    navigation<BottomNavRoutes.Home>(
        startDestination = HomeNavRoutes.HomeScreen
    ){
        composable<HomeNavRoutes.HomeScreen> {
            HomeScreen(paddingValues,navController)
        }
        composable <PlaylistDetailScreen>{
            val data=it.toRoute<PlaylistDetailScreen>()
            PlaylistScreen(navController,data)
        }

    }
}