package com.example.harmonyhub.features.home.data.respository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.home.data.local.dao.HomeDao
import com.example.harmonyhub.features.home.data.remote.models.HomeData
import com.example.harmonyhub.features.home.data.remote.models.HomeResponse

class HomeRepository(private val homeDao: HomeDao, private val networkService: NetworkService) {
    suspend fun getHomeData(): HomeData {
        if (networkService.isInternetAvailable()) {
            val homeResponse = ApiService.homeApi.getHomeData();
            val homeData=homeResponse.data ?: throw Exception("No Data Found");
            val homeEntity = homeData.toHomeEntity()
            homeDao.insertHomeData(homeEntity)
            return homeData
        } else {
            val homeEntity = homeDao.getHomeData() ?: throw Exception("No Local Data Found")
            val homeData = homeEntity.toHomeData()
            return homeData
        }
    }
    }