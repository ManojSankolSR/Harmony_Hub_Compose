package com.example.harmonyhub.features.music_player.data.remote.models

import com.google.gson.annotations.SerializedName

data class LyricsData(
    val source: String,
    val track: String,
    val artist: String,
    val album: String?,
    val duration: Double,
    val instrumental: Boolean,
    val plainLyrics: String?,
    val syncedLyrics: String?,
    val syncedAvailable: Boolean
)
