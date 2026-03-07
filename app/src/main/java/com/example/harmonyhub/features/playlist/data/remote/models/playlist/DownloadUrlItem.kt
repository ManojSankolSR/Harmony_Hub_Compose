package com.example.harmonyhub.features.playlist.data.remote.models.playlist

import kotlinx.serialization.Serializable

@Serializable
data class DownloadUrlItem(val link: String = "",
                           val quality: String = "")
