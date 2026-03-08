package com.example.harmonyhub.features.music_player.presentation.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.harmonyhub.features.home.presentation.components.MusicItemImage
import com.example.harmonyhub.features.music_player.presentation.components.QueueBottomSheet
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.PlayPauseControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.ProgressSeekBar
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekNextControl
import com.example.harmonyhub.features.music_player.presentation.components.player_controls.SeekPrevControl
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.home.data.remote.models.ArtistMap
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.getImageUrl


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerExpanded(
    closeExpandedPlayer: () -> Unit, modifier: Modifier = Modifier, viewModel: MusicPlayerViewModel, navController: NavHostController,
) {

    val mediaItem = viewModel.playerState.collectAsState().value.currentMediaItem


    Scaffold(
        topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Now Playing") },
            navigationIcon = {
                FilledTonalIconButton(closeExpandedPlayer) {
                    Icon(Icons.Default.KeyboardArrowDown, null, Modifier.size(36.dp))
                }
            })
    },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = { QueueBottomSheet(viewModel) },
        bottomBar = {
            BottomAppBar(viewModel)
        }

    ) {

        LazyColumn(
            modifier
                .padding(it)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 24.dp,horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                Column(
                    modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    MusicItemImage(
                        mediaItem?.getImageUrl(), modifier = Modifier
                            .height(350.dp)
                            .clip(RoundedCornerShape(14.dp))

                    )
                    Column() {
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
            }

            item {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    ProgressSeekBar(viewModel)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        SeekPrevControl(viewModel, 62.dp)
                        PlayPauseControl(viewModel, 62.dp)
                        SeekNextControl(viewModel, 62.dp)
                    }
                }

            }

            item {
                ArtistList(
                    mediaItem?.artistMap,
                    navController = navController,
                    musicPlayerViewModel = viewModel
                )
            }


        }

    }


}