package com.example.harmonyhub.features.music_player.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.harmonyhub.features.music_player.data.local.entities.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerStateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Query("DELETE FROM songs")
    suspend fun deleteAllSongs()

    @Transaction
    suspend fun updatePlayerState(songs: List<SongEntity>) {
        deleteAllSongs()
        insertSongs(songs)
    }

    @Query("UPDATE songs SET isPlaying = 1 WHERE id = :songId")
    suspend fun setCurrentlyPlaying(songId: String)

    @Query("UPDATE songs SET isPlaying = 0")
    suspend fun clearCurrentlyPlaying()

    @Query("SELECT * FROM songs WHERE isPlaying = 1 LIMIT 1")
    suspend fun getCurrentlyPlayingSong(): SongEntity?

    @Query("SELECT * FROM songs")
    suspend fun getPlayerQueue(): List<SongEntity>

}
