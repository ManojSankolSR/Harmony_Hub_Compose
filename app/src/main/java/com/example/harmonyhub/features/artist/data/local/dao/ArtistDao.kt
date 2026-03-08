package com.example.harmonyhub.features.artist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.features.artist.data.local.entity.ArtistEntity


@Dao
interface ArtistDao{

    @Query("SELECT * FROM artist_data WHERE id=:id")
    suspend fun getArtist(id: String): ArtistEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArtist(artistEntity: ArtistEntity)

    @Query("DELETE FROM artist_data WHERE id=:id")
    suspend fun deleteArtist(id: String)

    @Query("DELETE FROM artist_data")
    suspend fun clearArtists()

}
