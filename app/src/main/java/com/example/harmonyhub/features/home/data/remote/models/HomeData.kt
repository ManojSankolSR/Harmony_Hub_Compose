package com.example.harmonyhub.features.home.data.remote.models

data class HomeData(
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
){

    fun asList(): List<HomeDataItem> {
        return listOfNotNull(
            trending,
            charts,
            albums,
            cityMod,
            playlists,
            radio,
            artistRecos,
            globalConfig,
            mixes,
            promo0,
            promo1,
            promo2,
            promo3,
            promo4,
            promo5,
            promo6,
            discover,
        )
    }

}

