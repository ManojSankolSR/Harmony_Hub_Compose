package com.example.harmonyhub.features.artist.data.remote.api

import com.example.harmonyhub.features.artist.data.remote.models.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApi {

    @GET("/artist")
    suspend fun getArtistDetails(
        @Query("id") id: String
    ): ArtistResponse
}
