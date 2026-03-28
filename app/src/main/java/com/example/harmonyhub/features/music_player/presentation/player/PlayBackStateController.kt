package com.example.harmonyhub.features.music_player.presentation.player

import androidx.media3.exoplayer.ExoPlayer
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.features.music_player.presentation.state.MusicPlayerUiState
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.toMediaItem
import kotlinx.coroutines.delay

class PlayBackStateController(
    val player: ExoPlayer,
    val updateUiState: (MusicPlayerUiState.() -> MusicPlayerUiState) -> Unit
) {

    fun setMediaItems(songs: List<Song>, startIndex: Int = 0, quality: AudioQuality) {
        val mediaItems = songs.map { it.toMediaItem(quality) }
        player.setMediaItems(mediaItems)
        player.seekTo(startIndex, 0L)
        updateUiState() {
            copy(
                mediaItemQueue = songs,
                currentMediaItem = songs.getOrNull(startIndex),
                nextControlEnabled = songs.size > 1 && player.hasNextMediaItem(),
                prevControlEnabled = startIndex > 0 && player.hasPreviousMediaItem()
            )
        }

    }

    fun addMediaItemsToQueue(songs: List<Song>, quality: AudioQuality) {
        val mediaItems = songs.map { it.toMediaItem(quality) }
        player.addMediaItems(mediaItems)
        updateUiState() {
            copy(
                mediaItemQueue = mediaItemQueue + songs,
                nextControlEnabled = true
            )
        }

    }

    fun play() {
        player.prepare()
        player.play()
    }

    fun pause() {
        player.pause()
    }

    fun nextMediaItem() {
        if (player.hasNextMediaItem()) {
            player.seekToNextMediaItem()
            player.prepare()
            player.play()

        }
    }

    fun prevMediaItem() {
        if (player.hasPreviousMediaItem()) {
            player.seekToPreviousMediaItem()
            player.prepare()
            player.play()
        }
    }

    fun replay(isError: Boolean = false) {
        if (isError) {
            val currIndex = player.currentMediaItemIndex;
            player.seekTo(currIndex, 0L)
        } else {
            player.seekTo(0, 0L)
        }
        play()
    }


    fun seekTo(position: Long) {
        player.seekTo(position)
    }

    fun skip(milliseconds: Long){
        val finalPosition=player.currentPosition+milliseconds
        player.seekTo(finalPosition)

    }

    fun shuffle(currentQueue: List<Song>, quality: AudioQuality) {
        val shuffledQueue = currentQueue.shuffled()
        setMediaItems(shuffledQueue, quality = quality)
    }

    suspend fun addDurationListener(){
        while (true) {

            val position = player.currentPosition
            val duration = player.duration

            updateUiState {
                copy(
                    currentPosition = position,
                    duration = duration
                )
            }

            delay(500) // update every 500ms
        }
    }



}