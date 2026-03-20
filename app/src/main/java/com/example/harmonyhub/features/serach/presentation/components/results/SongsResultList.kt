package com.example.harmonyhub.features.serach.presentation.components.results

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.navigation.MusicItemNavigator
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.serach.data.remote.models.SongSearchItem
import com.example.harmonyhub.features.serach.data.remote.models.toMusicDataItem
import com.example.harmonyhub.features.serach.data.remote.models.toSong
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel
import kotlinx.coroutines.launch

fun LazyListScope.songsResultList(
    songs: List<SongSearchItem>,
    musicPlayerViewModel: MusicPlayerViewModel,
    navController: NavHostController,
    downloadsViewModel: DownloadsViewModel
) {
    if (songs.isNotEmpty()) {
        item {
            SectionTitle("Songs")
        }
        items(songs) { songItem ->
            SongResultItem(songItem, musicPlayerViewModel, navController,downloadsViewModel)
        }
    }
}

@Composable
private fun SongResultItem(
    songItem: SongSearchItem,
    musicPlayerViewModel: MusicPlayerViewModel,
    navController: NavHostController,
    downloadsViewModel: DownloadsViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val app = LocalContext.current.applicationContext as HarmonyHub
    val localPlaylistViewModel: LocalPlaylistViewModel = viewModel(
        factory = LocalPlaylistViewModelFactory(app.appContainer.localPlaylistRepository)
    )

    val mappedSong = songItem.toSong()

    fun onClick() {
        coroutineScope.launch {
            MusicItemNavigator.navigate(
                songItem.type,
                navController,
                songItem.toMusicDataItem(),
                musicPlayerViewModel
            )
        }
    }

    SongsListItem(
        song = mappedSong,
        viewModel = musicPlayerViewModel,
        onClick = ::onClick,
        localPlaylistViewModel = localPlaylistViewModel,
        downloadsViewModel=downloadsViewModel
    )
}
