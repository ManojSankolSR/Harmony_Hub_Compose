package com.example.harmonyhub.features.serach.presentation.components.results

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.artist.presentation.components.SectionTitle
import com.example.harmonyhub.features.local_palylist.presentation.components.AddToPlaylistBottomSheet
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.serach.data.remote.models.SongSearchItem
import com.example.harmonyhub.features.serach.data.remote.models.toSong
import kotlinx.coroutines.launch

fun LazyListScope.songsResultList(
    songs: List<SongSearchItem>,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    if (songs.isNotEmpty()) {
        item {
            SectionTitle("Songs")
        }
        items(songs) { songItem ->
            SongResultItem(songItem, musicPlayerViewModel)
        }
    }
}

@Composable
private fun SongResultItem(
    songItem: SongSearchItem,
    musicPlayerViewModel: MusicPlayerViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val app = LocalContext.current.applicationContext as HarmonyHub
    val repository = app.appContainer.songRepository
    val localPlaylistViewModel: LocalPlaylistViewModel = viewModel(
        factory = LocalPlaylistViewModelFactory(app.appContainer.localPlaylistRepository)
    )

    var songToAddToPlaylist by remember { mutableStateOf<Song?>(null) }

    val mappedSong = songItem.toSong()

    fun onClick() {
        coroutineScope.launch {
            isLoading = true
            val songs = repository.getSongs(songItem.id)
            musicPlayerViewModel.setMediaItems(songs, 0)
            musicPlayerViewModel.play()
            isLoading = false
        }
    }

    SongsListItem(
        song = mappedSong,
        viewModel = musicPlayerViewModel,
        onClick = ::onClick,
        onAddToPlaylist = { songToAddToPlaylist = mappedSong }
    )

    songToAddToPlaylist?.let { song ->
        AddToPlaylistBottomSheet(
            song = song,
            viewModel = localPlaylistViewModel,
            onDismiss = { songToAddToPlaylist = null }
        )
    }
}
