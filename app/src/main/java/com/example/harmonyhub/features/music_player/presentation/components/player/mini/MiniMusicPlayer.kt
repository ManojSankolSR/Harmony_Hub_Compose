package com.example.harmonyhub.features.music_player.presentation.componentsimport

import androidx.annotation.OptIn
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.PlayPauseControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.ProgressSeekBar
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekNextControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekPrevControl
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@OptIn(UnstableApi::class)
@Composable
fun MiniMusicPlayer(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    musicPlayerViewModel: MusicPlayerViewModel,
    expandedPlayer: () -> Unit
) {


    val playerState by musicPlayerViewModel.playerState.collectAsState()

    val currentMediaItem = playerState.currentMediaItem;

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable() {
                expandedPlayer()
            },

        verticalAlignment = Alignment.CenterVertically
    ) {
        MusicItemImage(
            song = currentMediaItem,
            modifier = Modifier.fillMaxWidth(0.14f)
        )
        Column() {
            ProgressSeekBar(musicPlayerViewModel, true)
            Row(
                modifier = modifier
                    .padding(end = 12.dp, start = 8.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        currentMediaItem?.name ?: "",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        currentMediaItem?.subtitle ?: "",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W400),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                SeekPrevControl(musicPlayerViewModel)
                PlayPauseControl(musicPlayerViewModel)
                SeekNextControl(musicPlayerViewModel)
            }
        }


    }

}