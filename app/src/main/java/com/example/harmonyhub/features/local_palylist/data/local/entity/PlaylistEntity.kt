package com.example.harmonyhub.features.local_palylist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("local_playlist")
data class PlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val createdDate: Long,
    val updatedDate: Long,
)
