package com.example.harmonyhub.features.local_palylist.data.local.entity

import androidx.room.Entity


@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossref(
    val playlistId: Int,
    val songId: String
)
