package com.example.harmonyhub.features.serach.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType
import com.google.gson.JsonElement


data class SongSearchItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val type: MusicItemType,
    val image: JsonElement? = null,
    val description: String? = null,
    val url: String,
    val album: String? = null,
    val ctr: Int = 0,
    val language: String? = null,
    val primaryArtists: String? = null,
    val singers: String? = null,
    val trillerAvailable: Boolean = false,
    val position: Int = 0
)

fun SongSearchItem.toMusicDataItem(): MusicDataItem {
    return MusicDataItem(
        id = id,
        name = name,
        subtitle = subtitle,
        type = type,
        url = url,
        image = image,
        description = description,
        album = album,
        language = language
    )
}
