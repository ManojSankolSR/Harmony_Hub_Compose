package com.example.harmonyhub.features.serach.data.remote.models

data class SearchCategory<T>(
    val position: Int,
    val data: List<T> = emptyList()
)