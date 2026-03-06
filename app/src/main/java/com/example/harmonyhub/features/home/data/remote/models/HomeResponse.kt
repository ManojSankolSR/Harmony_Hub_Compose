package com.example.harmonyhub.features.home.data.remote.models

data class HomeResponse(
    val data: HomeData,
    val message: String = "",
    val status: String = ""
)