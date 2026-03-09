package com.example.harmonyhub.features.album.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.harmonyhub.core.presentation.components.ErrorView
import com.example.harmonyhub.core.presentation.components.Loader
import com.example.harmonyhub.features.album.presentation.state.AlbumUiState
import com.example.harmonyhub.features.album.presentation.viewmodel.AlbumViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@Composable
fun AlbumContent(
    state: AlbumUiState,
    albumViewModel: AlbumViewModel,
    musicPlayerViewModel: MusicPlayerViewModel,
    navController: NavHostController,
    albumId: String,
    parentPaddingValues: PaddingValues
) {

    fun onRefresh() {
        albumViewModel.getAlbumDetails(albumId)
    }

    Scaffold(
        topBar = {
            TopBar(navController)
        }) { padding ->
        Box(Modifier.padding(padding)) {

            when (state) {
                is AlbumUiState.Loading -> {
                    Loader(padding)
                }

                is AlbumUiState.Success -> {
                    val playlistData = state.data;
                    AlbumSuccess(playlistData, musicPlayerViewModel,parentPaddingValues)
                }

                is AlbumUiState.Error -> {
                    val message = state.message
                    ErrorView(
                        onRefresh = ::onRefresh,
                        message,
                        paddingValues = PaddingValues(bottom = parentPaddingValues.calculateBottomPadding())
                    )

                }
            }


        }
    }
}