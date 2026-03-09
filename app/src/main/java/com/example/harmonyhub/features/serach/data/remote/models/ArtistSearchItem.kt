package com.example.harmonyhub.features.serach.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.google.gson.JsonElement

data class ArtistSearchItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val type: MusicItemType,
    val url: String,
    val image: JsonElement? = null, // Can be String or List<ImageItem>
    val description: String? = null,
    val ctr: Int = 0,
    val entity: Int = 0,
    val extra: String? = null,
    val position: Int = 0
)