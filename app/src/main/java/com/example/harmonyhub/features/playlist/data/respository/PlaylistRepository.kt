package com.example.harmonyhub.features.playlist.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.music_player.data.local.dao.PlayerStateDao
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistDetailsResponse

class PlaylistRepository(private val networkService: NetworkService) {

    suspend fun getPlaylistDetails(id: String): PlaylistDetailsResponse{
        if(networkService.isInternetAvailable()){
            return ApiService.playlistDetailsApi.getPlaylistDetails(id);
        }else{
            throw Exception("No internet connection");
        }

    }
}