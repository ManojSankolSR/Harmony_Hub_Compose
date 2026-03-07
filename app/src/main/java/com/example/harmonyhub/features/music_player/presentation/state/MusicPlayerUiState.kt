package com.example.harmonyhub.features.music_player.presentation.state

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

data class MusicPlayerUiState(
    val currentMediaItem: Song?,
    val playbackState: PlaybackState,
    val nextControlEnabled: Boolean,
    val prevControlEnabled: Boolean,
    val mediaItemQueue: List<Song>
)


