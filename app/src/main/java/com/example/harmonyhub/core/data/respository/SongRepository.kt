package com.example.harmonyhub.core.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

class SongRepository(private  val networkService: NetworkService) {

    suspend fun getSongs(id: String): List<Song>{
        val response= ApiService.songApi.getSong((id));
        val data=response.data ?: throw Exception("Song Not Found")
        val songs=data.songs.ifEmpty {
            throw Exception("Song Not Found")
        };
        return songs;
    }
}