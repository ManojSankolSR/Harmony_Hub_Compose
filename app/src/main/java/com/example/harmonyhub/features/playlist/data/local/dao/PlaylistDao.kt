package com.example.harmonyhub.features.playlist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.features.playlist.data.local.entity.PlaylistEntity
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistData


@Dao
interface PlaylistDao {

    @Query("SELECT * FROM playlist_data WHERE id = :id")
    suspend fun getPlaylistData(id: String): PlaylistEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlaylist(playlist: PlaylistEntity)

    @Query("DELETE FROM playlist_data WHERE id = :id")
    suspend fun deletePlaylist(id: String)

    @Query("DELETE FROM playlist_data")
    suspend fun clearPlaylist()
}