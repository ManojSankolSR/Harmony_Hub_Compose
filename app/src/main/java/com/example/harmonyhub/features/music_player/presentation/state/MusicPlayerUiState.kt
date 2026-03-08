package com.example.harmonyhub.features.music_player.presentation.state

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import kotlin.time.Duration

data class MusicPlayerUiState(
    val currentMediaItem: Song?,
    val playbackState: PlaybackState,
    val nextControlEnabled: Boolean,
    val prevControlEnabled: Boolean,
    val mediaItemQueue: List<Song>,
    val currentPosition:Long = 0L,
    val duration: Long=0L,
)


