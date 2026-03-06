package com.example.harmonyhub.features.playlist.data.remote.api

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface PlaylistDetailsApi {

    @GET("/playlist",)
    suspend fun getPlaylistDetails(
        @Query("id") id: String
    ): PlaylistDetailsResponse



}