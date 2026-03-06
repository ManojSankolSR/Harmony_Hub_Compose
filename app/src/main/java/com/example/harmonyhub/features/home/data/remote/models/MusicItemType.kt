package com.example.harmonyhub.features.home.data.remote.models

import com.google.gson.annotations.SerializedName

enum class MusicItemType {

    @SerializedName("artist")
    ARTIST,

    @SerializedName("album")
    ALBUM,

    @SerializedName("playlist")
    PLAYLIST,

    @SerializedName("radio")
    RADIO,

    @SerializedName("radio_station")
    RADIO_STATION,

    @SerializedName("song")
    SONG,

    @SerializedName("channel")
    CHANNEL,

    @SerializedName("mix")
    MIX,

    @SerializedName("show")
    SHOW,

    @SerializedName("episode")
    EPISODE,

    @SerializedName("season")
    SEASON,

    @SerializedName("label")
    LABEL
}