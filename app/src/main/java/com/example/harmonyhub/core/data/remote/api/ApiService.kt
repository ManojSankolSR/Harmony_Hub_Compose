package com.example.harmonyhub.core.data.remote.api

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
}

