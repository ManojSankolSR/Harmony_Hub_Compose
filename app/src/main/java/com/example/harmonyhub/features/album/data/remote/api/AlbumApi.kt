package com.example.harmonyhub.features.album.data.remote.api

import com.example.harmonyhub.features.album.data.remote.models.AlbumResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {

    @GET("/album")
    suspend fun getAlbumDetails(
        @Query("id") id: String
    ): AlbumResponse
    
}