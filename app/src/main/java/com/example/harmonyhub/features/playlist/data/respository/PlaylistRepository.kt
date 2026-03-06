package com.example.harmonyhub.features.playlist.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.features.playlist.data.remote.api.PlaylistDetailsApi
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistDetailsResponse

class PlaylistRepository() {

    suspend fun getPlaylistDetails(id: String): PlaylistDetailsResponse{
        return ApiService.playlistDetailsApi.getPlaylistDetails(id);
    }
}