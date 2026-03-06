package com.example.harmonyhub.navigation.bottom_bar_nav.search_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.harmonyhub.features.playlist.presentation.screens.PlaylistScreen
import com.example.harmonyhub.features.serach.presentation.screens.SearchScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.BottomNavRoutes
import com.example.harmonyhub.navigation.bottom_bar_nav.PlaylistDetailScreen



fun NavGraphBuilder.searchNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,

    ) {
    navigation<BottomNavRoutes.Search>(
        startDestination = SearchNavRoutes.SearchScreen
    ){
        composable<SearchNavRoutes.SearchScreen> {
            SearchScreen(paddingValues,navController)
        }
        composable<PlaylistDetailScreen>{
            val data=it.toRoute<PlaylistDetailScreen>()
            PlaylistScreen(navController,data)
        }

    }
}