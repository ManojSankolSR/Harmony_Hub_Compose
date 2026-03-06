package com.example.harmonyhub.features.home.data.remote.models

data class HomeDataItem(
    val title: String? = "",
    val featuredText: String? = "",
    val data: List<MusicDataItem?>? = null,
    val subtitle: String? = "",
    val position: Int? = 0,
    val source: String? = "",
)