package com.example.harmonyhub.features.album.data.repository

import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.album.data.local.dao.AlbumDao
import com.example.harmonyhub.features.album.data.local.entity.toAlbumData
import com.example.harmonyhub.features.album.data.local.entity.toAlbumEntity
import com.example.harmonyhub.features.album.data.remote.models.AlbumData

class AlbumRepository(val albumDao: AlbumDao, val networkService: NetworkService) {

    suspend fun getAlbumDetails(id: String): AlbumData {

        if (networkService.isInternetAvailable()) {
            val albumResponse = ApiService.albumDetailsApi.getAlbumDetails(id);
            val albumData = albumResponse.data ?: throw Exception("No Data Found");
            val albumEntity = albumData.toAlbumEntity()
            albumDao.addAlbum(albumEntity)
            return albumData;
        } else {
            val albumEntity = albumDao.getAlbum(id) ?: throw Exception("No Local Data Found");
            val albumData = albumEntity.toAlbumData()
            return albumData
        }
    }
}