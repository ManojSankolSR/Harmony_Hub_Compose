package com.example.harmonyhub.features.serach.data.remote.models.song

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

data class SongDetailsData(
    val songs: List<Song> = emptyList(),
    val modules: Map<String, SongModule>? = null
)