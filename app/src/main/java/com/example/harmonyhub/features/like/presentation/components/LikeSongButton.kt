package com.example.harmonyhub.features.like.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.like.presentation.state.LikedSongsUIState
import com.example.harmonyhub.features.like.presentation.viewmodel.LikedSongsViewModel
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

@Composable
fun LikeSongsButton(
    modifier: Modifier = Modifier,
    viewModel: LikedSongsViewModel,
    song: Song,
    iconSize: Dp = 32.dp,
) {
    val state = viewModel.state.collectAsState().value

    val isLiked = if (state is LikedSongsUIState.Success) {
        state.songs.any { it.id == song.id }
    } else {
        false
    }

    Box (
        modifier = modifier.clickable(onClick = {
            if (isLiked) {
                viewModel.unLikeSong(song.id)
            } else {
                viewModel.likeSong(song)
            }
        },)
    ) {
        Icon(
            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = if (isLiked) "Unlike" else "Like",
            tint = if (isLiked) Color.Red else MaterialTheme.colorScheme.onBackground
        )
    }
}