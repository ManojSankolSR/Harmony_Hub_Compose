package com.example.harmonyhub.features.music_player.presentation.components.player.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.local_palylist.presentation.viewmodel.LocalPlaylistViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.LyricsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerExpanded(
    closeExpandedPlayer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MusicPlayerViewModel,
    navController: NavHostController,
    lyricsViewModel: LyricsViewModel,
    localPlaylistViewModel: LocalPlaylistViewModel,
    downloadsViewModel: DownloadsViewModel,
) {

    val mediaItem = viewModel.playerState.collectAsState().value.currentMediaItem
    val paddingHorizontal = PaddingValues(horizontal = 20.dp)

    Scaffold(
        topBar = {
            TopBar(onCloseClick = closeExpandedPlayer)
        },
        floatingActionButtonPosition = FabPosition.EndOverlay,
        floatingActionButton = { QueueBottomSheet(viewModel) },
        bottomBar = {
            BottomAppBar(
                modifier,
                viewModel,
                localPlaylistViewModel,
                song = mediaItem ?: return@Scaffold,
                downloadsViewModel,
            )
        }
    ) { padding ->

        LazyColumn(
            modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 24.dp, horizontal = 0.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {
                SongInfo(song = mediaItem, paddingHorizontal = paddingHorizontal)
            }

            item {
                PlayerControls(viewModel = viewModel, paddingHorizontal = paddingHorizontal)
            }

            item {
                LyricsSection(
                    modifier = modifier,
                    viewModel = viewModel,
                    lyricsViewModel = lyricsViewModel,
                    paddingHorizontal = paddingHorizontal
                )
            }

            item {
                ArtistSection(
                    artistMap = mediaItem?.artistMap,
                    navController = navController,
                    viewModel = viewModel,
                    onArtistClick = closeExpandedPlayer,
                    paddingHorizontal = paddingHorizontal
                )
            }
        }
    }
}
