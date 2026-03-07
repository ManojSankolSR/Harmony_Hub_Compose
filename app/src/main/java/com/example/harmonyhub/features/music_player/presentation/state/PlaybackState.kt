package com.example.harmonyhub.features.music_player.presentation.state

sealed class PlaybackState {

    data object Playing:PlaybackState();
    data object Paused:PlaybackState();
    data object Completed:PlaybackState();

    data object Error: PlaybackState();

    data object Loading:PlaybackState();

}