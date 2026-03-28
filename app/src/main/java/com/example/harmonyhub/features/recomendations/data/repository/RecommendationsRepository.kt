package com.example.harmonyhub.features.recomendations.data.repository

import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.recomendations.data.remote.api.RecommendationsApi


class RecommendationsRepository(
    private val networkService: NetworkService,
    private val recommendationsApi: RecommendationsApi
) {

    suspend fun getSongRecommendations(id: String,languages: String): List<Song>{
        if(networkService.isInternetAvailable()){
            val response = recommendationsApi.getRecommendedSongs(id,languages)
            val songs=response.data;

            if(songs.isNullOrEmpty()){
                throw Exception("No songs found")
            }

            return songs
        }else{
            throw Exception("No internet connection found")
        }

    }
}