package com.example.harmonyhub.features.local_palylist.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistEntity
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistSongCrossref
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistWithSongs
import com.example.harmonyhub.features.music_player.data.local.entities.SongEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface LocalPlaylistDao {

    @Query("SELECT * FROM local_playlist")
    fun observePlaylistsWithSongs(): Flow<List<PlaylistWithSongs>>

    @Query("""
        SELECT songs.* FROM songs 
        INNER JOIN PlaylistSongCrossref ON songs.id = PlaylistSongCrossref.songId 
        WHERE PlaylistSongCrossref.playlistId = :playlistId
    """)
    fun observeSongsOfPlaylist(playlistId: Int): Flow<List<SongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSong(song: SongEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(crossRef: PlaylistSongCrossref)


    @Delete
    suspend fun deletePlaylist(playlist: PlaylistEntity)

    @Query("DELETE FROM PlaylistSongCrossref WHERE playlistId = :playlistId")
    suspend fun clearPlaylist(playlistId: Int)

    @Query("""
        DELETE FROM PlaylistSongCrossref
        WHERE playlistId = :playlistId AND songId = :songId
    """)
    suspend fun removeSongFromPlaylist(
        playlistId: Int,
        songId: String
    )


}
