package com.example.harmonyhub.features.serach.data.remote.models

import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem

data class TopSearchesResponse(
    val status: String,
    val message: String,
    val data: List<MusicDataItem>?
)
