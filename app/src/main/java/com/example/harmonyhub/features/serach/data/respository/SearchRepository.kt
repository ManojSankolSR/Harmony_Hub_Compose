package com.example.harmonyhub.features.serach.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.home.data.remote.models.MusicDataItem
import com.example.harmonyhub.features.serach.data.remote.models.SearchData

class SearchRepository(private val networkService: NetworkService) {

    suspend fun search(query: String): SearchData {
        val response = ApiService.searchApi.search(query)
        val data = response.data ?: throw Exception("No data found")
        return data
    }

    suspend fun getTopSearches(): List<MusicDataItem> {
        if (networkService.isInternetAvailable()) {
            val response = ApiService.searchApi.getTopSearches()
            val data = response.data ?: throw Exception("No data found")
            return data
        } else {
            throw Exception("No internet connection")
        }

    }
}
