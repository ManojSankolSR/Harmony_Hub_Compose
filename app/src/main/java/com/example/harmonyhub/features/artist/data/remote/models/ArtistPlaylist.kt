package com.example.harmonyhub.features.artist.data.remote.models

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

data class ArtistPlaylist(
    val id: String = "",
    val name: String = "",
    val subtitle: String = "",
    val type: String = "",
    val headerDesc: String = "",
    val url: String = "",
    val image: String = "",
    val explicit: Boolean = false,
    val userId: String = "",
    val isDolbyContent: Boolean? = null,
    val firstname: String = "",
    val lastname: String = "",
    val songs: List<Song>? = null
)