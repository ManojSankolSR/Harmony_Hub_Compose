package com.example.harmonyhub.features.playlist.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.playlist.data.local.dao.PlaylistDao
import com.example.harmonyhub.features.playlist.data.local.entity.toEntity
import com.example.harmonyhub.features.playlist.data.local.entity.toPlaylistData
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistDetailsResponse

class PlaylistRepository(private val playlistDao: PlaylistDao, private val networkService: NetworkService) {

    suspend fun getPlaylistDetails(id: String): PlaylistDetailsResponse{
        if(networkService.isInternetAvailable()){
            val playlistDetailsResponse= ApiService.playlistDetailsApi.getPlaylistDetails(id);
            val playlistEntity=  playlistDetailsResponse.data.toEntity();
            playlistDao.addPlaylist(playlistEntity)
            return playlistDetailsResponse;
        }else{
            val playlistEntity= playlistDao.getPlaylistData(id) ?: throw Exception("No Local Data Found");
            val playlistDetails= playlistEntity.toPlaylistData()
            return PlaylistDetailsResponse(playlistDetails, message = "✅ OK", status = "Success")
        }

    }
}