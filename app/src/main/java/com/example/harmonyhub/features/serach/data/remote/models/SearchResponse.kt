package com.example.harmonyhub.features.serach.data.remote.models

data class SearchResponse(
    val status: String,
    val message: String,
    val data: SearchData?
)

