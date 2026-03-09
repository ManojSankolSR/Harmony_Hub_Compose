package com.example.harmonyhub.features.serach.data.remote.api


import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.serach.data.remote.models.SearchResponse
import com.example.harmonyhub.features.serach.data.remote.models.TopSearchesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/search")
    suspend fun search(
        @Query("q") q: String
    ): SearchResponse

    @GET("/search/top")
    suspend fun getTopSearches(): TopSearchesResponse

}