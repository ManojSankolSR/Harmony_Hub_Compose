package com.example.harmonyhub.features.music_player.presentation.components.lyrics

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.harmonyhub.features.music_player.data.remote.models.Lyrics
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@Composable
fun LyricsContent(
    lyrics: Lyrics,
    playerViewModel: MusicPlayerViewModel
) {
    when (lyrics) {
        is Lyrics.Synced -> {
            SyncedLyricsList(
                lines = lyrics.lines,
                playerViewModel = playerViewModel
            )
        }
        is Lyrics.Plain -> {
            PlainLyricsView(text = lyrics.text)
        }
        Lyrics.None -> {
            Text(
                text = "No lyrics available",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
