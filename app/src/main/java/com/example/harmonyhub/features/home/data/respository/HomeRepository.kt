package com.example.harmonyhub.features.home.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.home.data.local.dao.HomeDao
import com.example.harmonyhub.features.home.data.remote.models.HomeResponse

class HomeRepository(private val homeDao: HomeDao, private val networkService: NetworkService) {
    suspend fun getHomeData(): HomeResponse {
        if (networkService.isInternetAvailable()) {
            val homeResponse = ApiService.homeApi.getHomeData();
            val homeEntity = homeResponse.data.toHomeEntity()
            homeDao.insertHomeData(homeEntity)
            return homeResponse
        } else {
            val homeEntity = homeDao.getHomeData() ?: throw Exception("No Local Data Found")
            val homeData = homeEntity.toHomeData()
            return HomeResponse(
                data = homeData,
                message = "✅ OK",
                status = "Success"
            )
        }
    }
    }