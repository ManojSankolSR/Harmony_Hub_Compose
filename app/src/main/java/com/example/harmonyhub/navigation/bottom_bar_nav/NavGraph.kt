package com.example.harmonyhub.navigation.bottom_bar_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.viewmodel.AuthViewModel
import com.example.harmonyhub.features.music_player.presentation.components.MusicPlayer
import com.example.harmonyhub.features.music_player.presentation.componentsimport.MiniMusicPlayer
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModelFactory

import com.example.harmonyhub.navigation.bottom_bar_nav.home_nav.homeNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.library_nav.libraryNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.search_nav.searchNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav.settingsNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBarNavGraph(parentNavController: NavHostController, authViewModel: AuthViewModel) {

    val navController = rememberNavController();

    val app= LocalContext.current.applicationContext as HarmonyHub;

    val playerRepository=app.appContainer.playerRepository;

    val musicPlayerViewModel :  MusicPlayerViewModel= viewModel(
        factory = MusicPlayerViewModelFactory(app,playerRepository)
    )

    Scaffold(
        bottomBar = {
            Column(
                verticalArrangement = Arrangement.Bottom
            ){
                MusicPlayer(
                    navController,
                    musicPlayerViewModel = musicPlayerViewModel
                )
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavRoutes.Home,
        ) {
            homeNavGraph(navController, paddingValues,musicPlayerViewModel)
            searchNavGraph(navController, paddingValues,musicPlayerViewModel)
            libraryNavGraph(navController, paddingValues,musicPlayerViewModel)
            settingsNavGraph(navController, paddingValues, authViewModel,musicPlayerViewModel)
        }
    }


}