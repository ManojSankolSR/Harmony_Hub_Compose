package com.example.harmonyhub.features.playlist.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.playlist.data.local.dao.PlaylistDao
import com.example.harmonyhub.features.playlist.data.local.entity.toEntity
import com.example.harmonyhub.features.playlist.data.local.entity.toPlaylistData
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistData

class PlaylistRepository(private val playlistDao: PlaylistDao, private val networkService: NetworkService) {

    suspend fun getPlaylistDetails(id: String): PlaylistData{
        if(networkService.isInternetAvailable()){
            val playlistDetailsResponse = ApiService.playlistDetailsApi.getPlaylistDetails(id);
            val playlistData= playlistDetailsResponse.data ?: throw Exception("No Data Found");
            val playlistEntity=  playlistData.toEntity();
            playlistDao.addPlaylist(playlistEntity)
            return playlistData;
        }else{
            val playlistEntity= playlistDao.getPlaylist(id) ?: throw Exception("No Local Data Found");
            val playlistData= playlistEntity.toPlaylistData()
            return playlistData
        }

    }
}