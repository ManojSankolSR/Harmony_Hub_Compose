package com.example.harmonyhub.features.music_player.data.remote.models

sealed class Lyrics {

    data class Synced(
        val lines: List<SyncedLyric>
    ) : Lyrics()

    data class Plain(
        val text: String
    ) : Lyrics()

    data object None : Lyrics()
}