package com.example.harmonyhub.features.serach.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.ImageItem
import com.example.harmonyhub.features.home.data.remote.models.MusicItemType

data class ShowSearchItem(
    val id: String,
    val name: String,
    val subtitle: String,
    val type: MusicItemType,
    val image: List<ImageItem>? = null,
    val description: String? = null,
    val url: String,
    val position: Int = 0,
    val seasonNumber: Int? = null
)