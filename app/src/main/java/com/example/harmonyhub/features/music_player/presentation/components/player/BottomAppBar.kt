package com.example.harmonyhub.features.music_player.presentation.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.outlined.LibraryAdd
import androidx.compose.material.icons.outlined.Shuffle
import androidx.compose.material.icons.rounded.AddRoad
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.Forward10
import androidx.compose.material.icons.rounded.LibraryAdd
import androidx.compose.material.icons.rounded.Replay10
import androidx.compose.material.icons.rounded.Shuffle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.offline.Download
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@Composable
fun BottomAppBar(viewModel: MusicPlayerViewModel, modifier: Modifier = Modifier) {
    fun skipPrev(milliseconds: Long = -10_000) {
        viewModel.skip(milliseconds)

    }

    fun skipNext(milliseconds: Long = 10_000) {
        viewModel.skip(milliseconds)

    }

    fun shuffle() {
        viewModel.shuffle()
    }

    fun download() {
        viewModel.shuffle()
    }


    fun addToPlaylist(){

    }

    val iconSize = 32.dp;
    val iconColor= MaterialTheme.colorScheme.primary
    BottomAppBar(
        modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ){
            IconButton(onClick = ::skipPrev,) {
                Icon(Icons.Rounded.Replay10, null, modifier = modifier.size(iconSize))
            }

            IconButton(onClick = ::skipNext, ) {
                Icon(Icons.Rounded.Forward10, null, modifier = modifier.size(iconSize))
            }

            IconButton(onClick = ::shuffle) {
                Icon(Icons.Rounded.Shuffle, null, modifier = modifier.size(iconSize))
            }

            IconButton(onClick = ::addToPlaylist) {
                Icon(Icons.Outlined.LibraryAdd, null, modifier = modifier.size(iconSize))
            }

            IconButton(onClick = ::download) {
                Icon(Icons.Rounded.ArrowDownward, null, modifier = modifier.size(iconSize))
            }




        }

    }

}