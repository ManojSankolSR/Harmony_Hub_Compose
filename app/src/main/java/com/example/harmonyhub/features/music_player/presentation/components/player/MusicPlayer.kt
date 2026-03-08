package com.example.harmonyhub.features.music_player.presentation.components.player

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harmonyhub.features.music_player.presentation.componentsimport.MiniMusicPlayer
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel


@Composable
fun MusicPlayer(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    musicPlayerViewModel: MusicPlayerViewModel
) {

    var isExpanded by remember { mutableStateOf(false) }

    val closeExpandedPlayer: () -> Unit = {
        isExpanded = false;
    }

    val expandedPlayer: () -> Unit = {
        isExpanded = true;
    }


    val mediaItem=musicPlayerViewModel.playerState.collectAsState().value.currentMediaItem



    Box(Modifier
        .fillMaxWidth().then(
            if (isExpanded) {
                Modifier
            } else {
                Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            }
        )
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.surfaceContainer).animateContentSize()
    ) {
        if(mediaItem==null) return;
        if (isExpanded) {
            MusicPlayerExpanded(
                closeExpandedPlayer,
                modifier,
                musicPlayerViewModel,
                navController
            )
        } else {
            MiniMusicPlayer(
                navController,
                modifier,
                musicPlayerViewModel,
                expandedPlayer
            )
        }

    }

}

