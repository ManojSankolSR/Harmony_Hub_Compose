package com.example.harmonyhub.features.local_palylist.data.repository

import androidx.room.Transaction
import com.example.harmonyhub.features.local_palylist.data.local.dao.LocalPlaylistDao
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistEntity
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistSongCrossref
import com.example.harmonyhub.features.local_palylist.data.local.entity.PlaylistWithSongs
import com.example.harmonyhub.features.music_player.data.local.entities.SongEntity
import com.example.harmonyhub.features.music_player.data.local.entities.toEntity
import com.example.harmonyhub.features.music_player.data.local.entities.toSong
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPlaylistRepository(private val dao: LocalPlaylistDao) {
    fun observePlaylistWithSongs(): Flow<List<PlaylistWithSongs>> {
        return dao.observePlaylistsWithSongs()
    }

    fun observeSongsOfPlaylist(playlistId: Int): Flow<List<Song>> {
        val songsEntity=dao.observeSongsOfPlaylist(playlistId);
        val songs=songsEntity.map { seList ->
            seList.map { se-> se.toSong() } }
        return  songs
    }

    suspend fun addPlaylist(name: String) {
        val playlist = PlaylistEntity(
            name = name,
            createdDate = System.currentTimeMillis(),
            updatedDate = System.currentTimeMillis()
        )
        dao.insertPlaylist(playlist)
    }

    @Transaction
    suspend fun addSongToPlaylist(playlistId: Int, song: Song) {
        val songEntity = song.toEntity()
        dao.insertSong(songEntity)
        val crossRef = PlaylistSongCrossref(
            playlistId = playlistId,
            songId = song.id ?: ""
        )
        dao.insertCrossRef(crossRef)
    }

    @Transaction
    suspend fun deletePlaylist(playlistEntity: PlaylistEntity) {
        dao.deletePlaylist(playlistEntity)
        dao.clearPlaylist(playlistId = playlistEntity.id)
    }

    suspend fun deleteSongFromPlaylist(playlistId: Int, songId: String) {
        dao.removeSongFromPlaylist(playlistId, songId)
    }
}
