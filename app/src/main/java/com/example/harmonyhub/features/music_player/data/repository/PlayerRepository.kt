package com.example.harmonyhub.features.music_player.data.repository

import android.util.Log
import com.example.harmonyhub.features.music_player.data.local.dao.PlayerStateDao
import com.example.harmonyhub.features.music_player.data.local.entities.toEntity
import com.example.harmonyhub.features.music_player.data.local.entities.toSong
import com.example.harmonyhub.features.music_player.presentation.state.MusicPlayerUiState
import com.example.harmonyhub.features.music_player.presentation.state.PlaybackState
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song

class PlayerRepository(private val dao: PlayerStateDao) {

    suspend fun savePlayerState(state: List<Song>, id: String) {

        Log.d("getPlayerStateFromLocal ", "togetPlayerStateFromLocal: $state")
        val data=state.map { it.toEntity(id==it.id) }
        dao.updatePlayerState(data)
    }

    suspend fun getPlayerState(): MusicPlayerUiState {
        val mediaItems = dao.getPlayerQueue().map{ it.toSong() }
        val currentMediaItem= dao.getCurrentlyPlayingSong()?.toSong()


        return MusicPlayerUiState(
            currentMediaItem = currentMediaItem,
            playbackState = PlaybackState.Paused,
            nextControlEnabled = true,
            prevControlEnabled = true,
            mediaItemQueue = mediaItems
        )

    }


}