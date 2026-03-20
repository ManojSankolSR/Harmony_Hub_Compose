package com.example.harmonyhub.features.radio.data.repository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.radio.data.remote.models.create_radio_station.CreateRadioStationData
import com.example.harmonyhub.features.radio.data.remote.models.radio_station_songs.RadioStationSongsData

class RadioRepository(
    private val networkService: NetworkService
) {
    suspend fun createRadioStation(
        path: String,
        id: String,
        name: String,
        type: String,
        lang: String
    ): CreateRadioStationData {
        val response = ApiService.radioApi.createRadioStation(
            path, id, name, type, lang
        )
        val data = response.data ?: throw Exception("Unable to create radio station")
        return data;
    }

    suspend fun getRadioStationSongs(stationId: String): RadioStationSongsData{
        val response = ApiService.radioApi.getRadioSongs(stationId)
        val data=response.data ?: throw Exception("Unable to get radio station songs")
        return data;
    }

    suspend fun createRadioStationAndGetSongs(
        musicDataItem: MusicDataItem
    ):List<Song> {
        val path=musicDataItem.featured_station_type
        val id=musicDataItem.id
        val name=musicDataItem.name
        val type=musicDataItem.featured_station_type
        val lang=musicDataItem.language ?: "english,hindi"
        if(networkService.isInternetAvailable()){
            val stationData=createRadioStation(path, id, name, type, lang)
            val songsData= getRadioStationSongs(stationData.stationId)
            val songs=songsData.songs
            return songs;
        }else{
            throw Exception("No internet connection")
        }

    }
}