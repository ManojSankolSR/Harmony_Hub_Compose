package com.example.harmonyhub.features.music_player.data.remote.api

import com.example.harmonyhub.features.music_player.data.remote.models.LyricsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface LyricsApi {

    @GET("/get/synced-lyrics")
    suspend fun getLyrics(
        @Query("id") id: String,
        @Query("link") link: String,
        @Query("track") track: String,
        ): LyricsResponse
}

