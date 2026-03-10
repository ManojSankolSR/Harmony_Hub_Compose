package com.example.harmonyhub.features.serach.data.remote.api

import com.example.harmonyhub.features.serach.data.remote.models.song.SongDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface SongApi {
    @GET("/song")
    suspend fun getSong(
        @Query("id") id: String
    ): SongDetailsResponse
}
