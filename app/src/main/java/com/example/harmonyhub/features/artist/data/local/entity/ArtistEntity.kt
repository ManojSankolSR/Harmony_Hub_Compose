package com.example.harmonyhub.features.artist.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonyhub.features.artist.data.remote.models.ArtistData
import com.google.gson.JsonElement

@Entity(tableName = "artist_data")
data class ArtistEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val subtitle: String,
    val image: JsonElement?,
    val followerCount: Int,
    val type: String,
    val isVerified: Boolean,
    val dominantLanguage: String,
    val dominantType: String,
    val fanCount: Int,
    val isFollowed: Boolean
)

fun ArtistEntity.toArtistData(): ArtistData {
    return ArtistData(
        id = id,
        name = name,
        subtitle = subtitle,
        image = image,
        followerCount = followerCount,
        type = type,
        isVerified = isVerified,
        dominantLanguage = dominantLanguage,
        dominantType = dominantType,
        fanCount = fanCount,
        isFollowed = isFollowed
    )
}

fun ArtistData.toArtistEntity(): ArtistEntity {
    return ArtistEntity(
        id = id,
        name = name,
        subtitle = subtitle,
        image = image,
        followerCount = followerCount,
        type = type,
        isVerified = isVerified,
        dominantLanguage = dominantLanguage,
        dominantType = dominantType,
        fanCount = fanCount,
        isFollowed = isFollowed
    )
}
