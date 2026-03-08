package com.example.harmonyhub.features.artist.data.remote.models

import com.google.gson.JsonElement

data class SimilarArtist(
    val id: String = "",
    val name: String = "",
    val roles: Map<String, String>? = null,
    val aka: String = "",
    val fb: String = "",
    val twitter: String = "",
    val wiki: String = "",
    val similar: List<SimilarArtistInfo>? = null,
    val dob: String = "",
    val image: JsonElement? = null,
    val searchKeywords: String = "",
    val primaryArtistId: String = "",
    val languages: Map<String, String>? = null,
    val url: String = "",
    val type: String = "",
    val isRadioPresent: Boolean = false,
    val dominantType: String = ""
)