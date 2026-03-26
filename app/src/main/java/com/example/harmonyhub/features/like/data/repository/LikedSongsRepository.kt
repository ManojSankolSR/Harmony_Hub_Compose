package com.example.harmonyhub.features.like.data.repository

import com.example.harmonyhub.features.like.data.local.dao.LikedSongsDao
import com.example.harmonyhub.features.like.data.local.entities.LikedSongEntity
import com.example.harmonyhub.features.like.data.local.entities.toLikedSongEntity
import com.example.harmonyhub.features.like.data.local.entities.toSong
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LikedSongsRepository(private val dao: LikedSongsDao)  {

    fun observeLikedSongs(): Flow<List<Song>> {
        return dao.getLikedSongs().map { entities ->
            entities.map { it.toSong() }
        }
    }


    suspend fun addLikedSong(song: Song){
        val entity=song.toLikedSongEntity()
        dao.addLikedSong(entity)
    }

    suspend fun deleteLikedSong(songId: String) {
        dao.removeLikedSong(songId)
    }

}