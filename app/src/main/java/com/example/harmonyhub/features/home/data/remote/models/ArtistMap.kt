package com.example.harmonyhub.features.home.data.remote.models

data class ArtistMap(
    val artists: List<ArtistsItem>? = emptyList(),
    val featured_artists: List<ArtistsItem>? = emptyList(),
    val primary_artists: List<ArtistsItem>? = emptyList()
)
