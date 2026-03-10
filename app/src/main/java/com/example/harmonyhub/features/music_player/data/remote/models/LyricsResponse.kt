package com.example.harmonyhub.features.music_player.data.remote.models

data class LyricsResponse(
    val status: String,
    val message: String,
    val data: LyricsData?
)
