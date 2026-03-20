package com.example.harmonyhub.features.radio.data.remote.models.radio_station_songs

data class RadioStationSongsResponse(
    val status: String,
    val message: String,
    val data: RadioStationSongsData?=null
)

