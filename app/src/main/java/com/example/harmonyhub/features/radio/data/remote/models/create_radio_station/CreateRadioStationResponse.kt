package com.example.harmonyhub.features.radio.data.remote.models.create_radio_station

data class CreateRadioStationResponse(
    val status: String,
    val message: String,
    val data: CreateRadioStationData?=null
)

