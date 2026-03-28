package com.example.harmonyhub.features.recomendations.data.remote.api

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.recomendations.data.remote.models.SongRecommendationsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendationsApi {

    @GET("/song/recommend")
    suspend fun getRecommendedSongs(
        @Query("id") id: String,
        @Query("lang") lang: String,
    ): SongRecommendationsResponse
}
