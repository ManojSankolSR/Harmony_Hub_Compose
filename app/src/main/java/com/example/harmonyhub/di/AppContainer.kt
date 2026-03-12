package com.example.harmonyhub.di

import android.content.Context
import com.example.harmonyhub.core.data.local.db.AppDatabase
import com.example.harmonyhub.core.data.respository.SongRepository
import com.example.harmonyhub.core.data.respository.UserRepository
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.album.data.repository.AlbumRepository
import com.example.harmonyhub.features.artist.data.repository.ArtistRepository
import com.example.harmonyhub.features.home.data.respository.HomeRepository
import com.example.harmonyhub.features.local_palylist.data.repository.LocalPlaylistRepository
import com.example.harmonyhub.features.music_player.data.repository.LyricsRepository
import com.example.harmonyhub.features.music_player.data.repository.PlayerRepository
import com.example.harmonyhub.features.playlist.data.respository.PlaylistRepository
import com.example.harmonyhub.features.serach.data.respository.SearchRepository
import com.example.harmonyhub.features.song_download.data.repository.DownloadRepository

class AppContainer(val context: Context) {

    val networkService= NetworkService(context)

    val db: AppDatabase = AppDatabase.getInstance(context)

    val userRepository = UserRepository(db.userDao())

    val playerRepository= PlayerRepository(db.songDao())

    val homeRepository = HomeRepository(db.homeDao(),networkService);

    val playlistRepository = PlaylistRepository(db.playlistDao(),networkService=networkService );

    val albumRepository= AlbumRepository(db.albumDao(),networkService)

    val artistRepository= ArtistRepository(db.artistDao(),networkService)

    val searchRepository= SearchRepository(networkService)

    val songRepository= SongRepository(networkService)

    val lyricsRepository= LyricsRepository(networkService)

    val localPlaylistRepository = LocalPlaylistRepository(db.localPlaylistDao())

    val downloadRepository= DownloadRepository(context,networkService)

}
