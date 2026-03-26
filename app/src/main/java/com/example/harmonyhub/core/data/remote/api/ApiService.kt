package com.example.harmonyhub.core.data.remote.api

import com.example.harmonyhub.features.album.data.remote.api.AlbumApi
import com.example.harmonyhub.features.app_update.data.remote.api.AppUpdateApi
import com.example.harmonyhub.features.artist.data.remote.api.ArtistApi
import com.example.harmonyhub.features.home.data.remote.api.HomeApi
import com.example.harmonyhub.features.music_player.data.remote.api.LyricsApi
import com.example.harmonyhub.features.playlist.data.remote.api.PlaylistDetailsApi
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistDetailsResponse
import com.example.harmonyhub.features.radio.data.remote.api.RadioApi
import com.example.harmonyhub.features.serach.data.remote.api.SearchApi
import com.example.harmonyhub.features.serach.data.remote.api.SongApi
import com.example.harmonyhub.features.song_download.data.remote.DownloadApi

object ApiService {

    val homeApi: HomeApi by lazy {
        RetrofitClient.retrofit.create<HomeApi>(HomeApi::class.java)
    }

    val playlistDetailsApi:PlaylistDetailsApi by lazy {
        RetrofitClient.retrofit.create<PlaylistDetailsApi>(PlaylistDetailsApi::class.java)
    }

    val albumDetailsApi: AlbumApi by lazy {
        RetrofitClient.retrofit.create<AlbumApi>(AlbumApi::class.java)
    }


    val artistDetailsApi: ArtistApi by lazy {
        RetrofitClient.retrofit.create<ArtistApi>(ArtistApi::class.java)
    }

    val searchApi: SearchApi by lazy {
        RetrofitClient.retrofit.create<SearchApi>(SearchApi::class.java)
    }

    val songApi: SongApi by lazy {
        RetrofitClient.retrofit.create<SongApi>(SongApi::class.java)
    }

    val lyricsApi : LyricsApi by lazy {
        RetrofitClient.retrofit.create<LyricsApi>(LyricsApi::class.java)
    }

    val downloadApi: DownloadApi by lazy {
        RetrofitClient.retrofit.create<DownloadApi>(DownloadApi::class.java)
    }

    val radioApi: RadioApi by lazy {
        RetrofitClient.retrofit.create<RadioApi>(RadioApi::class.java)
    }

    val appUpdateApi: AppUpdateApi by lazy {
        RetrofitClient.retrofit.create<AppUpdateApi>(AppUpdateApi::class.java)
    }

}

