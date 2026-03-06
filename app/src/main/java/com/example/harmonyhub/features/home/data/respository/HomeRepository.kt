package com.example.harmonyhub.features.home.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.features.home.data.remote.models.HomeResponse

class HomeRepository() {
    suspend fun getHomeData(): HomeResponse{
        return ApiService.homeApi.getHomeData();
    }

}