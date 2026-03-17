package com.example.harmonyhub.features.song_download.data.repository

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.DownloadUrlItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Rights
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.getImageUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import org.jaudiotagger.tag.TagOptionSingleton
import org.jaudiotagger.tag.images.ArtworkFactory
import java.io.File
import java.io.FileOutputStream

class DownloadRepository(
    private val context: Context,
    private val networkService: NetworkService
) {

    private fun createFile(fileName: String): File {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)!!
        if (!dir.exists()) dir.mkdirs()
        val safeName = fileName.replace("[^a-zA-Z0-9._-]".toRegex(), "_")
        return File(dir, "$safeName.m4a")
    }

    suspend fun writeMetadata(song: Song, file: File) =
        withContext(Dispatchers.IO) {
            TagOptionSingleton.getInstance().isAndroid = true

            val tempFile = File(file.parentFile, "${file.nameWithoutExtension}_tmp.m4a")

            try {
                file.copyTo(tempFile, overwrite = true)

                val audioFile = AudioFileIO.read(tempFile)
                val tag = audioFile.tagOrCreateAndSetDefault


                song.id.takeIf { it.isNotBlank() }?.let {
                    tag.setField(FieldKey.COMMENT,it)
                }
                song.name.takeIf { it.isNotBlank() }?.let { tag.setField(FieldKey.TITLE, it) }
                song.subtitle.takeIf { it.isNotBlank() }?.let { tag.setField(FieldKey.ARTIST, it) }

                song.getImageUrl()?.let { url ->
                    try {
                        val conn = java.net.URL(url).openConnection()
                        conn.connectTimeout = 5000
                        conn.readTimeout = 5000
                        val bytes = conn.getInputStream().use { it.readBytes() }
                        val artFile = File.createTempFile("art", ".jpg", file.parentFile)
                        artFile.writeBytes(bytes)
                        tag.setField(ArtworkFactory.createArtworkFromFile(artFile))
                        artFile.delete()
                    } catch (e: Exception) {
                        Log.e("Download", "Artwork failed: ${e.message}")
                    }
                }

                audioFile.commit()
                tempFile.renameTo(file)

            } catch (e: Exception) {
                Log.e("Download", "Error writing metadata: ${e.message}")
                tempFile.delete()
            }
        }


    suspend fun downloadSong(song: Song, quality: AudioQuality) =
        withContext(Dispatchers.IO) {
            if (!networkService.isInternetAvailable()) {
                throw Exception("No internet connection")
            }

            val link = song.downloadUrl?.find { it.quality == quality.kbps }?.link
                    ?: song.downloadUrl?.lastOrNull()?.link
                    ?: throw Exception("Download link not found")

            val file = createFile(song.name)
            val response = ApiService.downloadApi.downloadSong(link)

            if (!response.isSuccessful) {
                throw Exception("Download failed")
            }

            response.body()?.byteStream()?.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
            writeMetadata(song, file)
        }

    suspend fun deleteSong(song: Song) = withContext(Dispatchers.IO) {
        val filePath = song.downloadUrl?.firstOrNull()?.link ?: return@withContext
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
    }

    suspend fun getDownloadedSongs(): List<Song> = withContext(Dispatchers.IO) {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC) ?: return@withContext emptyList()
        val files = dir.listFiles { file -> file.extension == "m4a" || file.extension == "mp3" }
            ?: return@withContext emptyList()

        files.mapNotNull { file ->
            try {
                val audioFile = AudioFileIO.read(file)
                val tag = audioFile.tag
                val id=tag.getFirst(FieldKey.COMMENT)?.takeIf { it.isNotBlank() } ?: "0"
                val title = tag?.getFirst(FieldKey.TITLE)?.takeIf { it.isNotBlank() } ?: file.nameWithoutExtension
                val artist = tag?.getFirst(FieldKey.ARTIST)?.takeIf { it.isNotBlank() } ?: "Unknown"
                val bitmap = tag.firstArtwork.binaryData.let {
                    BitmapFactory.decodeByteArray(it, 0, it.size)
                }

                Log.d("Download", "Found song: $id")

                Song(
                    id = id,
                    imageBitmap = bitmap,
                    name = title,
                    subtitle = artist,
                    downloadUrl = listOf(DownloadUrlItem(link = file.absolutePath)),
                    rights = Rights(),
                )
            } catch (e: Exception) {
                Log.e("Download", "Error reading file ${file.name}: ${e.message}")
                null
            }
        }
    }
}
