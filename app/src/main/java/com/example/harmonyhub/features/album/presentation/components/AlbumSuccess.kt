package com.example.harmonyhub.features.album.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harmonyhub.HarmonyHub
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.album.data.remote.models.AlbumData
import com.example.harmonyhub.features.album.data.remote.models.getImageUrl
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModelFactory
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@Composable
fun AlbumSuccess(
    data: AlbumData,
    musicPlayerViewModel: MusicPlayerViewModel,
    parentPaddingValues: PaddingValues,
    downloadsViewModel: DownloadsViewModel
) {
    val app = LocalContext.current.applicationContext as HarmonyHub
    val localPlaylistViewModel: LocalPlaylistViewModel = viewModel(
        factory = LocalPlaylistViewModelFactory(app.appContainer.localPlaylistRepository)
    )

    val onClick: (songs: List<Song>, index: Int) -> Unit = { songs, index ->
        musicPlayerViewModel.setMediaItems(songs = songs, index)
        musicPlayerViewModel.play()
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(bottom = parentPaddingValues.calculateBottomPadding())
    ) {
        item {
            AlbumHeader(
                data.getImageUrl(),
                title = data.name,
                subtitle = data.headerDesc,
                subtitleDes = data.artistMap?.artists?.map { it.name }
            )
        }
        item {
            Text(
                "Songs", style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W500, color = MaterialTheme.colorScheme.primary
                ), modifier = Modifier.padding(8.dp)
            )
        }
        itemsIndexed(items = data.songs ?: emptyList()) { index, song ->
            SongsListItem(
                song,
                onClick = { onClick(data.songs!!, index) },
                viewModel = musicPlayerViewModel,
                localPlaylistViewModel = localPlaylistViewModel,
                downloadsViewModel=downloadsViewModel
            )
        }
    }
}
