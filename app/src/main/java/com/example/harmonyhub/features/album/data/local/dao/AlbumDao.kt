package com.example.harmonyhub.features.album.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.features.album.data.local.entity.AlbumEntity


@Dao
interface AlbumDao {

    @Query("SELECT * FROM album_data WHERE id = :id")
    suspend fun getAlbum(id: String): AlbumEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbum(albumEntity: AlbumEntity)

    @Query("DELETE FROM album_data WHERE id = :id")
    suspend fun deleteAlbum(id: String)

    @Query("DELETE FROM album_data")
    suspend fun clearAlbums()

}