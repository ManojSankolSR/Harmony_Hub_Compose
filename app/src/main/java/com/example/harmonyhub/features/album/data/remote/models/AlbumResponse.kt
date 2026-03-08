package com.example.harmonyhub.features.album.data.remote.models

data class AlbumResponse(
    val status: String = "",
    val message: String = "",
    val data: AlbumData? = null
)

