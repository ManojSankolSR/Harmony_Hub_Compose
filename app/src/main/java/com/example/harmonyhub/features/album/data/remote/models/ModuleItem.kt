package com.example.harmonyhub.features.album.data.remote.models

data class ModuleItem(
    val title: String = "",
    val subtitle: String = "",
    val position: Int = 0,
    val source: String = "",
    val params: Map<String, String>? = null
)