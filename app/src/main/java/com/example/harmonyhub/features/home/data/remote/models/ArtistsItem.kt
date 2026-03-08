package com.example.harmonyhub.features.home.data.remote.models

import com.example.harmonyhub.features.artist.data.remote.models.ArtistData
import com.google.gson.JsonElement

data class ArtistsItem(
    val id: String = "",
    val name: String = "",
    val url: String = "",
    val role: String = "",
    val type: String = "",
    val image: JsonElement? = null
)


fun ArtistsItem.toMusicDataItem(): MusicDataItem {
    return MusicDataItem(
        id = id,
        name = name,
        url = url,
        type = MusicItemType.ARTIST,
        image = image
    )
}
