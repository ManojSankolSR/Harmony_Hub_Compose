package com.example.harmonyhub.features.serach.data.remote.models.song

data class SongModule(
    val title: String = "",
    val subtitle: String = "",
    val position: Int = 0,
    val source: String = "",
    val params: Map<String, String>? = null
)