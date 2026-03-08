package com.example.harmonyhub.features.home.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonyhub.features.home.data.remote.models.HomeData
import com.example.harmonyhub.features.home.data.remote.models.HomeDataItem

@Entity(tableName = "home_data")
data class HomeEntity(
    @PrimaryKey val id: Int = 0,
    val trending: HomeDataItem?,
    val charts: HomeDataItem?,
    val albums: HomeDataItem?,
    val cityMod: HomeDataItem?,
    val playlists: HomeDataItem?,
    val radio: HomeDataItem?,
    val artistRecos: HomeDataItem?,
    val globalConfig: HomeDataItem?,
    val mixes: HomeDataItem?,
    val promo0: HomeDataItem?,
    val promo1: HomeDataItem?,
    val promo2: HomeDataItem?,
    val promo3: HomeDataItem?,
    val promo4: HomeDataItem?,
    val promo5: HomeDataItem?,
    val promo6: HomeDataItem?,
    val discover: HomeDataItem?,
) {
    fun toHomeData(): HomeData {
        return HomeData(
            trending = trending,
            charts = charts,
            albums = albums,
            cityMod = cityMod,
            playlists = playlists,
            radio = radio,
            artistRecos = artistRecos,
            globalConfig = globalConfig,
            mixes = mixes,
            promo0 = promo0,
            promo1 = promo1,
            promo2 = promo2,
            promo3 = promo3,
            promo4 = promo4,
            promo5 = promo5,
            promo6 = promo6,
            discover = discover
        )
    }
}
