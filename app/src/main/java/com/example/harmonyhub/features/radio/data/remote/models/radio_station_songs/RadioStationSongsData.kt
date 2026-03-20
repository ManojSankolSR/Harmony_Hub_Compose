package com.example.harmonyhub.features.radio.data.remote.models.radio_station_songs

import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

data class RadioStationSongsData(
    val stationId: String,
    val songs: List<Song>
)