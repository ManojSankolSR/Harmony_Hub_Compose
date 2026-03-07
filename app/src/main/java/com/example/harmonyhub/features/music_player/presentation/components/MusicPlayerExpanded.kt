package com.example.harmonyhub.features.music_player.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.PlayPauseControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.ProgressSeekBar
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekNextControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekPrevControl
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.getImageUrl


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerExpanded(
    togglePlayer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel
) {

    val mediaItem = viewModel.playerState.collectAsState().value.currentMediaItem



    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(mediaItem?.album ?: "", maxLines = 1) },
                navigationIcon = {
                    IconButton(togglePlayer) {
                        Icon(Icons.Default.KeyboardArrowDown, null, Modifier.size(36.dp))
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = { QueueBottomSheet(viewModel) },
        bottomBar = {
            BottomAppBar() {

            }
        }

    ) {

        LazyColumn(
            modifier
                .padding(it)
                .padding(horizontal = 25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                MusicItemImage(
                    mediaItem?.getImageUrl(),
                    modifier = Modifier

                        .height(350.dp)

                        .clip(RoundedCornerShape(14.dp))

                )
            }
            item {
                Column(
                    modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        mediaItem?.name ?: "",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W600),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        mediaItem?.subtitle ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }



            item {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    ProgressSeekBar(viewModel)
                    Row() {
                        SeekPrevControl(viewModel, 62.dp)
                        PlayPauseControl(viewModel, 62.dp)
                        SeekNextControl(viewModel, 62.dp)
                    }
                }

            }
        }

    }


}