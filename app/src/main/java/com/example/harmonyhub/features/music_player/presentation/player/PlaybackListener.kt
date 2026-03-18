package com.example.harmonyhub.features.music_player.presentation.player

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.harmonyhub.features.music_player.presentation.state.MusicPlayerUiState
import com.example.harmonyhub.features.music_player.presentation.state.PlaybackState

class PlaybackListener(
    private val player: ExoPlayer,
    private val updateUiState: (MusicPlayerUiState.() -> MusicPlayerUiState) -> Unit,
    private val updateCurrentMediaItem: (index: Int) -> Unit,
    private val savePlayerStateToLocal: () -> Unit
) : Player.Listener {

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
                if (player.isPlaying) {
                    updateUiState {
                        copy(playbackState = PlaybackState.Loading)
                    }
                }
            }
            Player.STATE_ENDED -> {
                updateUiState {
                    copy(playbackState = PlaybackState.Completed)
                }
            }
            Player.STATE_IDLE -> {
                updateUiState {
                    copy(playbackState = PlaybackState.Error)
                }
            }
        }
    }

    override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
        super.onMediaItemTransition(mediaItem, reason)
        val index = player.currentMediaItemIndex;
        updateCurrentMediaItem(index)
        updateUiState {
            copy(
                nextControlEnabled = player.hasNextMediaItem(),
                prevControlEnabled = player.hasPreviousMediaItem()
            )
        }
    }

    override fun onIsPlayingChanged(isPlaying: Boolean) {
        savePlayerStateToLocal()
        val state = when {
            player.playbackState == Player.STATE_BUFFERING -> PlaybackState.Loading
            isPlaying -> PlaybackState.Playing
            else -> PlaybackState.Paused
        }

        updateUiState {
            copy(playbackState = state)
        }
        updateUiState {
            copy(
                playbackState =state
            )
        }
    }


}