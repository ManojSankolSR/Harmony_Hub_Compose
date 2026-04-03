package com.example.harmonyhub.features.like.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.core.presentation.components.SongsListItem
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.song_download.presentation.viewmodel.DownloadsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikedSongItem(
    song: Song,
    musicPlayerViewModel: MusicPlayerViewModel,
    likedSongsViewModel: LikedSongsViewModel,
    downloadsViewModel: DownloadsViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dismissState = rememberSwipeToDismissBoxState(
        positionalThreshold = {
            it * .4f
        },
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                likedSongsViewModel.unLikeSong(song.id)
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.Settled -> Color.Red.copy(alpha = 0.8f)
                    SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.onSurface
                    else -> Color.Transparent
                }, label = "dismissBackground"
            )
            val scale by animateFloatAsState(
                if (dismissState.targetValue == SwipeToDismissBoxValue.Settled) 1f else 1.3f,
                label = "dismissScale"
            )

            Box(
                Modifier
                    .fillMaxSize()
//                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Unlike Icon",
                    modifier = Modifier.scale(scale),
                    tint = color
                )
            }
        },
        modifier = modifier
    ) {
        SongsListItem(
            song = song,
            viewModel = musicPlayerViewModel,
            onClick = onClick,
            downloadsViewModel = downloadsViewModel,
            backgroundColor = MaterialTheme.colorScheme.background
        )
    }
}
