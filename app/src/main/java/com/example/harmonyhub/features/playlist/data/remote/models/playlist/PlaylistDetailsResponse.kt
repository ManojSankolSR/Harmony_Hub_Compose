package com.example.harmonyhub.features.playlist.data.remote.models.playlist

data class PlaylistDetailsResponse(val data: PlaylistData?,
                                   val message: String = "",
                                   val status: String = "")