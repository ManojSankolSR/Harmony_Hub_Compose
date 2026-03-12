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
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModelFactory
import com.example.harmonyhub.features.music_player.data.repository.LyricsRepository
import com.example.harmonyhub.features.music_player.presentation.components.player.MusicPlayer
import com.example.harmonyhub.features.music_player.presentation.viewmodel.LyricsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.LyricsViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModelFactory
import com.example.harmonyhub.features.serach.presentation.viewmodel.SearchViewModel
import com.example.harmonyhub.features.serach.presentation.viewmodel.SearchViewModelFactory
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModelFactory

import com.example.harmonyhub.navigation.bottom_bar_nav.home_nav.homeNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.library_nav.libraryNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.search_nav.searchNavGraph
import com.example.harmonyhub.navigation.bottom_bar_nav.settings_nav.settingsNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomBarNavGraph(parentNavController: NavHostController, authViewModel: AuthViewModel) {

    val navController = rememberNavController();

    val context = LocalContext.current
    val app = context.applicationContext as HarmonyHub

    val playerRepository = app.appContainer.playerRepository
    val repository = app.appContainer.searchRepository
    val localPlaylistRepository=app.appContainer.localPlaylistRepository
    val downloadRepository=app.appContainer.downloadRepository;

    val musicPlayerViewModel: MusicPlayerViewModel = viewModel(
        factory = MusicPlayerViewModelFactory(app, playerRepository)
    )

    val lyricsViewModel: LyricsViewModel = viewModel(
        factory = LyricsViewModelFactory(LyricsRepository(NetworkService(context)))
    )

    val searchViewModel: SearchViewModel = viewModel(factory = SearchViewModelFactory(repository))


    val localPlaylistViewModel: LocalPlaylistViewModel = viewModel(
        factory = LocalPlaylistViewModelFactory(localPlaylistRepository)
    )

    val downloadsViewModel: DownloadsViewModel = viewModel(
        factory = DownloadsViewModelFactory(app.appContainer.downloadRepository)
    )

    Scaffold(
        bottomBar = {
            Column(
                verticalArrangement = Arrangement.Bottom
            ) {
                MusicPlayer(
                    navController,
                    musicPlayerViewModel = musicPlayerViewModel,
                    lyricsViewModel = lyricsViewModel,
                    localPlaylistViewModel=localPlaylistViewModel,
                    downloadsViewModel=downloadsViewModel,

                )
                BottomBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavRoutes.Home,
        ) {
            homeNavGraph(navController, paddingValues, musicPlayerViewModel)
            searchNavGraph(navController, paddingValues, musicPlayerViewModel, searchViewModel)
            libraryNavGraph(navController, paddingValues, musicPlayerViewModel,localPlaylistViewModel,downloadsViewModel)
            settingsNavGraph(navController, paddingValues, authViewModel, musicPlayerViewModel)
        }
    }


}
