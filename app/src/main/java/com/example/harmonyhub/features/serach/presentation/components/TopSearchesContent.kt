package com.example.harmonyhub.features.serach.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.presentation.components.top_searches.TopSearchesGrid
import com.example.harmonyhub.features.serach.presentation.state.TopSearchUiState

@Composable
fun TopSearchesContent(
    parentPaddingValues: PaddingValues,
    state: TopSearchUiState,
    navController: NavHostController,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    when (state) {
        is TopSearchUiState.Loading -> Loader(
            padding = PaddingValues( parentPaddingValues.calculateBottomPadding())
        )
        is TopSearchUiState.Error -> ErrorView(
            message = state.message,
            onRefresh = { },
            paddingValues = PaddingValues(bottom = parentPaddingValues.calculateBottomPadding())
        )

        is TopSearchUiState.Success -> {
            TopSearchesGrid(
                data = state.data,
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel,
                parentPaddingValues
            )
        }
    }
}
