package com.example.harmonyhub.features.music_player.data.repository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.music_player.data.remote.models.Lyrics
import com.example.harmonyhub.features.music_player.data.remote.models.LyricsData
import com.example.harmonyhub.features.music_player.data.remote.models.SyncedLyric
import retrofit2.HttpException

class LyricsRepository(private val networkService: NetworkService) {

    fun parseSyncedLyrics(lrc: String): List<SyncedLyric> {

        val regex = Regex("""\[(\d+):(\d+\.\d+)](.*)""")

        return lrc.lines().mapNotNull { line ->

            val match = regex.find(line) ?: return@mapNotNull null

            val minutes = match.groupValues[1].toLong()
            val seconds = match.groupValues[2].toDouble()

            val timeMs = (minutes * 60 * 1000 + seconds * 1000).toLong()

            SyncedLyric(
                timeMs = timeMs,
                text = match.groupValues[3].trim()
            )
        }
    }

    private fun parseLyrics(
        syncedLyrics: String?,
        plainLyrics: String?
    ): Lyrics {

        if (!syncedLyrics.isNullOrBlank()) {
            val parsed = parseSyncedLyrics(syncedLyrics)
            return Lyrics.Synced(parsed)
        }

        if (!plainLyrics.isNullOrBlank()) {
            return Lyrics.Plain(plainLyrics)
        }

        return Lyrics.None
    }


    suspend fun getLyrics(id: String, link: String, track: String): Lyrics {
        if (networkService.isInternetAvailable()) {
            try {
                val response = ApiService.lyricsApi.getLyrics(id, link, track)
                val data = response.data ?: throw Exception("No Lyrics Found")
                val parsedLyrics = parseLyrics(data.syncedLyrics, data.plainLyrics)
                return parsedLyrics
            }catch (e: HttpException){
                if(e.code()==400){
                    throw Exception("No Lyrics Found")
                }else{
                    throw e
                }
            }

        } else {
            throw Exception("No internet connection")
        }
    }

}