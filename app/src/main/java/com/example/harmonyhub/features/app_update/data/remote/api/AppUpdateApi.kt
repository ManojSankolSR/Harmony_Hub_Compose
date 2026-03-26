package com.example.harmonyhub.features.app_update.data.remote.api

import com.example.harmonyhub.features.app_update.data.remote.models.AppUpdateInfo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface AppUpdateApi {

    @GET
    suspend fun checkForUpdates(
        @Url url: String
    ): AppUpdateInfo

    @GET
    @Streaming
    suspend fun downloadUpdate(
        @Url url: String
    ): Response<ResponseBody>
}