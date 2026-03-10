package com.example.harmonyhub.features.music_player.presentation.components.lyrics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.harmonyhub.features.music_player.data.remote.models.SyncedLyric
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel
import kotlinx.coroutines.launch

@Composable
fun SyncedLyricsList(
    lines: List<SyncedLyric>,
    playerViewModel: MusicPlayerViewModel,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    // Collecting currentPosition here so ONLY this composable recomposes on every tick.
    val currentPosition by playerViewModel.playerState.collectAsStateWithLifecycle()
    val pos = currentPosition.currentPosition

    // Calculate active index locally.
    val activeIndex = remember(pos, lines) {
        lines.indexOfLast { it.timeMs <= pos }
    }

    LaunchedEffect(activeIndex) {
        if (activeIndex >= 0) {
            coroutineScope.launch {
                listState.animateScrollToItem(
                    index = activeIndex,
                    scrollOffset = -600
                )
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(0.dp),
        contentPadding = PaddingValues(vertical = 0.dp)
    ) {
        itemsIndexed(
            items = lines,
            key = { _, line -> line.timeMs }
        ) { index, line ->
            SyncedLyricItem(
                line = line,
                isActive = index == activeIndex,
                onClick = { playerViewModel.seekTo(line.timeMs) }
            )
        }
    }
}
