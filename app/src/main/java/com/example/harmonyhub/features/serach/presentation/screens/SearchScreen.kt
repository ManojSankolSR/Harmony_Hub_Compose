package com.example.harmonyhub.features.serach.presentation.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.presentation.components.SearchResultsContent
import com.example.harmonyhub.features.serach.presentation.components.TopBar
import com.example.harmonyhub.features.serach.presentation.components.TopSearchesContent
import com.example.harmonyhub.features.serach.presentation.viewmodel.SearchViewModel
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@Composable
fun SearchScreen(
    parentPaddingValues: PaddingValues,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel,
    searchViewModel: SearchViewModel,
    downloadsViewModel: DownloadsViewModel
) {

    val query by searchViewModel.query.collectAsState()

    val searchState by searchViewModel.searchState.collectAsStateWithLifecycle()

    val topSearchesState by searchViewModel.topSearchesState.collectAsStateWithLifecycle()

    val focusManager = LocalFocusManager.current


    Scaffold(
        modifier = Modifier.padding(top = parentPaddingValues.calculateTopPadding()),
        topBar = {
            TopBar(query,searchViewModel,focusManager)
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(top = padding.calculateTopPadding())
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
        ) {
            if (query.isEmpty()) {
                TopSearchesContent(parentPaddingValues,topSearchesState, navController, musicPlayerViewModel)
            } else {
                SearchResultsContent(padding,parentPaddingValues,searchState, navController, musicPlayerViewModel,downloadsViewModel)
            }
        }
    }
}
