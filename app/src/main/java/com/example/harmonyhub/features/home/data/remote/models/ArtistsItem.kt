package com.example.harmonyhub.features.home.data.remote.models

import com.google.gson.JsonElement

data class ArtistsItem(
    val id: String = "",
    val name: String = "",
    val url: String = "",
    val role: String = "",
    val type: String = "",
    val image: JsonElement? = null
)
