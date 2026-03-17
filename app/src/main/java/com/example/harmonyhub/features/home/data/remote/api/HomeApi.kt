package com.example.harmonyhub.features.home.data.remote.api

import com.example.harmonyhub.features.home.data.remote.models.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("/modules")
    suspend fun getHomeData(
        @Query("lang") lang: String
    ): HomeResponse
}