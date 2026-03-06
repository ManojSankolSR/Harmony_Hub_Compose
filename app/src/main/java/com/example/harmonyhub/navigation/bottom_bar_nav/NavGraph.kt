package com.example.harmonyhub.navigation.bottom_bar_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.library.presentation.screens.LibraryScreen
import com.example.harmonyhub.features.music_player.presentation.components.MiniMusicPlayer
import com.example.harmonyhub.features.settings.presentation.screens.SettingsScreen
import com.example.harmonyhub.navigation.bottom_bar_nav.home_nav.homeNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.library_nav.libraryNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.search_nav.searchNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav.settingsNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBarNavGraph(parentNavController: NavHostController, authViewModel: AuthViewModel) {

    val navController = rememberNavController();

    Scaffold(
        bottomBar = {
            Column(){
                MiniMusicPlayer()
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavRoutes.Home,
        ) {
            homeNavGraph(navController, paddingValues)
            searchNavGraph(navController, paddingValues)
            libraryNavGraph(navController, paddingValues)
            settingsNavGraph(navController, paddingValues, authViewModel)
        }
    }


}