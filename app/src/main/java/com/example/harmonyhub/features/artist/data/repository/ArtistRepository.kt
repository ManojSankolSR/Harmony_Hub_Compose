package com.example.harmonyhub.features.artist.data.repository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.artist.data.local.dao.ArtistDao
import com.example.harmonyhub.features.artist.data.local.entity.toArtistData
import com.example.harmonyhub.features.artist.data.local.entity.toArtistEntity
import com.example.harmonyhub.features.artist.data.remote.models.ArtistData

class ArtistRepository(private  val artistDao: ArtistDao,private val networkService: NetworkService) {

    suspend fun getArtistDetails(id: String): ArtistData{
        if(networkService.isInternetAvailable()){
            val response= ApiService.artistDetailsApi.getArtistDetails(id)
            val data=response.data ?: throw Exception("No data found")
            val entity=data.toArtistEntity();
            artistDao.addArtist(entity)
            return data
        }else{
            val entity=artistDao.getArtist(id) ?: throw Exception("No data found")
            val data= entity.toArtistData()
            return  data;
        }
    }
}