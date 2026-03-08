package com.example.harmonyhub.features.home.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.features.home.data.local.entity.HomeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface HomeDao {

    @Query("SELECT * FROM home_data")
    suspend fun getHomeData(): HomeEntity?

    @Query("DELETE FROM home_data")
    suspend fun deleteHomeData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(homeData: HomeEntity)

    suspend fun insertHomeData(homeData: HomeEntity) {
        deleteHomeData()
        insert(homeData)
    }
}