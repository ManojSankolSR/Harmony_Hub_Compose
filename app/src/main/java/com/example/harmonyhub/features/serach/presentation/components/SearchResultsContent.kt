package com.example.harmonyhub.features.serach.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.presentation.components.results.SearchResultsList
import com.example.harmonyhub.features.serach.presentation.state.SearchUiState

@Composable
fun SearchResultsContent(
    parentPaddingValues: PaddingValues,
    state: SearchUiState,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    when (state) {
        is SearchUiState.Loading -> Loader()
        is SearchUiState.Error -> ErrorView(
            message = state.message,
            onRefresh = { },
            paddingValues = PaddingValues(0.dp)
        )

        is SearchUiState.Success -> {
            SearchResultsList(parentPaddingValues, state, musicPlayerViewModel, navController)
        }
    }
}

