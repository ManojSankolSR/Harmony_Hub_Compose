package com.example.harmonyhub.features.music_player.presentation.components.lyrics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.harmonyhub.features.music_player.data.remote.models.Lyrics
import com.example.harmonyhub.features.music_player.presentation.viewmodel.MusicPlayerViewModel

@Composable
fun LyricsContent(
    lyrics: Lyrics,
    playerViewModel: MusicPlayerViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(450.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
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
}
