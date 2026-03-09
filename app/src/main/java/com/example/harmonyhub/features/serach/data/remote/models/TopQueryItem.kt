package com.example.harmonyhub.features.serach.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.google.gson.JsonElement

data class TopQueryItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val type: MusicItemType,
    val image: JsonElement? = null, // Can be String or List<ImageItem>
    val description: String? = null,
    val url: String,
    val language: String? = null,
    val primaryArtists: String? = null,
    val singers: String? = null,
    val trillerAvailable: Boolean? = null,
    val album: String? = null,
    val ctr: Int? = null,
    val position: Int = 0
)