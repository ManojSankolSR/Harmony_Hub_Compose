package com.example.harmonyhub.features.local_palylist.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class PlaylistWithSongs(
    @Embedded
    val playlist: PlaylistEntity,


    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = PlaylistSongCrossref::class,
            parentColumn = "playlistId",
            entityColumn = "songId"
        )
    )
    val songs: List<LocalSongEntity>,
)