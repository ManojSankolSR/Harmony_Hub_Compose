package com.example.harmonyhub.features.serach.presentation.components.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.presentation.state.SearchUiState
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@Composable
fun SearchResultsList(
    parentPaddingValues: PaddingValues,
    state: SearchUiState.Success,
    musicPlayerViewModel: MusicPlayerViewModel,
    navController: NavHostController,
    downloadsViewModel: DownloadsViewModel
) {
    LazyColumn(
        modifier = Modifier.Companion.fillMaxSize(),
        contentPadding = PaddingValues(bottom = parentPaddingValues.calculateBottomPadding()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        state.data.songs?.data?.let { songs ->
            songsResultList(
                songs = songs,
                musicPlayerViewModel = musicPlayerViewModel,
                navController,
                downloadsViewModel=downloadsViewModel
            )
        }

        state.data.albums?.data?.let { albums ->
            albumsResultList(
                albums = albums,
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel
            )
        }


        state.data.artists?.data?.let { artists ->
            artistsResultList(
                artists = artists,
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel
            )
        }


        state.data.playlists?.data?.let { playlists ->
            playlistsResultList(
                playlists = playlists,
                navController = navController,
                musicPlayerViewModel = musicPlayerViewModel
            )
        }
    }
}