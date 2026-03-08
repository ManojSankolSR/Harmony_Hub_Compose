package com.example.harmonyhub.features.artist.data.remote.models

data class ArtistResponse(
    val status: String = "",
    val message: String = "",
    val data: ArtistData? = null
)
