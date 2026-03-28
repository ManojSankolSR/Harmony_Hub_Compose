package com.example.harmonyhub.features.recomendations.data.remote.models

import com.example.harmonyhub.features.music_player.data.remote.models.LyricsData
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

data class SongRecommendationsResponse(
    val status: String,
    val message: String,
    val data: List<Song>?
)
