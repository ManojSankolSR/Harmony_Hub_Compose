package com.example.harmonyhub.features.like.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.features.like.data.local.entities.LikedSongEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LikedSongsDao {

    @Query("SELECT * FROM liked_songs")
    fun getLikedSongs(): Flow<List<LikedSongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLikedSong(song: LikedSongEntity)

    @Query("DELETE FROM liked_songs WHERE id = :songId")
    suspend fun removeLikedSong(songId: String)
}