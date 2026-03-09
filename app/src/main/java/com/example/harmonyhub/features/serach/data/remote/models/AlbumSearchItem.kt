package com.example.harmonyhub.features.serach.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.ImageItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType

data class AlbumSearchItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val type: MusicItemType,
    val image: List<ImageItem>? = null,
    val url: String,
    val ctr: Int = 0,
    val isMovie: Boolean = false,
    val language: String,
    val music: String? = null,
    val songPids: String? = null,
    val year: Int = 0,
    val position: Int = 0
)