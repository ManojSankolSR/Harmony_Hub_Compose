package com.example.harmonyhub.features.radio.data.remote.api

import com.example.harmonyhub.features.radio.data.remote.models.create_radio_station.CreateRadioStationResponse
import com.example.harmonyhub.features.radio.data.remote.models.radio_station_songs.RadioStationSongsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RadioApi {

    @GET("/radio/{path}")
    suspend fun createRadioStation(
        @Path("path") path: String,
        @Query("id") id: String,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("lang") lang: String
    ): CreateRadioStationResponse

    @GET("/radio/songs")
    suspend fun getRadioSongs(
        @Query("id") id: String,
    ): RadioStationSongsResponse

}