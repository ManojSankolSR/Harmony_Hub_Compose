package com.example.harmonyhub.core.data.remote.api

import com.example.harmonyhub.features.album.data.remote.api.AlbumApi
import com.example.harmonyhub.features.artist.data.remote.api.ArtistApi
import com.example.harmonyhub.features.home.data.remote.api.HomeApi
import com.example.harmonyhub.features.playlist.data.remote.api.PlaylistDetailsApi
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.PlaylistDetailsResponse

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

}

