package com.example.harmonyhub.core.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.presentation.state.DownloadedSongsUiState
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun DownloadSongButton (
    modifier: Modifier= Modifier,
    viewModel: DownloadsViewModel,
    song: Song,
    iconSize: Dp = 32.dp,
){

    val songsState = viewModel.uiStateDownloadedSongs.collectAsState().value

    fun onDownload() {
        viewModel.downloadSong(song)
    }

    fun onRefresh(){
        viewModel.getDownloadedSongs()
    }


    when(songsState){
        is DownloadedSongsUiState.Loading -> {
            IconButton(onClick = ::onRefresh){
                CircularProgressIndicator(modifier.size(iconSize))
            }
        }
        is DownloadedSongsUiState.Error -> {
            IconButton(onClick = ::onRefresh){
                Icon(Icons.Default.ErrorOutline,null)
            }
        }
        is DownloadedSongsUiState.Success -> {

            val isDownloaded = songsState.songs.any { it.id == song.id }

            IconButton(onClick = ::onDownload,enabled = !isDownloaded) {
                Icon(
                    if (isDownloaded) {
                        Icons.Rounded.DownloadDone
                    } else {
                        Icons.Outlined.ArrowDownward
                    }, null, modifier = modifier.size(iconSize)
                )
            }
        }
    }
}