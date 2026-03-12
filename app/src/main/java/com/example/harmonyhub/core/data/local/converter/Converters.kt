package com.example.harmonyhub.core.data.local.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.album.data.remote.models.AlbumModules
import com.example.harmonyhub.features.artist.data.remote.models.SimilarArtist
import com.example.harmonyhub.features.artist.data.remote.models.SimilarArtistInfo
import com.example.harmonyhub.features.home.data.remote.models.HomeDataItem
import com.example.harmonyhub.features.home.data.remote.models.ArtistMap
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.ArtistsItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.DownloadUrlItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Modules
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Rights
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Song
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream

class Converters(){
    @TypeConverter
    fun  fromAudioQuality(audioQuality: AudioQuality): String{
        return audioQuality.name;
    }

    @TypeConverter
    fun toAudioQuality(audioQuality: String): AudioQuality{
        return AudioQuality.valueOf(audioQuality)
    }

    @TypeConverter
    fun  fromLanguage(language: Language): String{
        return language.name;
    }

    @TypeConverter
    fun toLanguage(language: String): Language{
        return Language.valueOf(language)
    }

    @TypeConverter
    fun fromRights(rights: Rights?): String? = Gson().toJson(rights)

    @TypeConverter
    fun toRights(rightsString: String?): Rights? = Gson().fromJson(rightsString, Rights::class.java)

    @TypeConverter
    fun fromDownloadUrlList(list: List<DownloadUrlItem>?): String? = Gson().toJson(list)

    @TypeConverter
    fun toDownloadUrlList(listString: String?): List<DownloadUrlItem>? {
        val type = object : TypeToken<List<DownloadUrlItem>>() {}.type
        return Gson().fromJson(listString, type)
    }

    @TypeConverter
    fun fromArtistMap(artistMap: ArtistMap?): String? = Gson().toJson(artistMap)

    @TypeConverter
    fun toArtistMap(artistMapString: String?): ArtistMap? = Gson().fromJson(artistMapString, ArtistMap::class.java)

    @TypeConverter
    fun fromHomeDataItem(item: HomeDataItem?): String? = Gson().toJson(item)

    @TypeConverter
    fun toHomeDataItem(itemString: String?): HomeDataItem? = Gson().fromJson(itemString, HomeDataItem::class.java)

    @TypeConverter
    fun fromStringList(list: List<String>?): String? = Gson().toJson(list)

    @TypeConverter
    fun toStringList(listString: String?): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(listString, type)
    }

    @TypeConverter
    fun fromArtistsItemList(list: List<ArtistsItem>?): String? = Gson().toJson(list)

    @TypeConverter
    fun toArtistsItemList(listString: String?): List<ArtistsItem>? {
        val type = object : TypeToken<List<ArtistsItem>>() {}.type
        return Gson().fromJson(listString, type)
    }

    @TypeConverter
    fun fromModules(modules: Modules?): String? = Gson().toJson(modules)

    @TypeConverter
    fun toModules(modulesString: String?): Modules? = Gson().fromJson(modulesString, Modules::class.java)

    @TypeConverter
    fun fromAlbumModules(modules: AlbumModules?): String? = Gson().toJson(modules)

    @TypeConverter
    fun toAlbumModules(modulesString: String?): AlbumModules? = Gson().fromJson(modulesString, AlbumModules::class.java)

    @TypeConverter
    fun fromSongList(list: List<Song>?): String? = Gson().toJson(list)

    @TypeConverter
    fun toSongList(listString: String?): List<Song>? {
        val type = object : TypeToken<List<Song>>() {}.type
        return Gson().fromJson(listString, type)
    }

    @TypeConverter
    fun fromJsonElement(jsonElement: JsonElement?): String? = Gson().toJson(jsonElement)

    @TypeConverter
    fun toJsonElement(jsonString: String?): JsonElement? = Gson().fromJson(jsonString, JsonElement::class.java)

    @TypeConverter
    fun fromSimilarArtistList(list: List<SimilarArtist>?): String? = Gson().toJson(list)

    @TypeConverter
    fun toSimilarArtistList(listString: String?): List<SimilarArtist>? {
        val type = object : TypeToken<List<SimilarArtist>>() {}.type
        return Gson().fromJson(listString, type)
    }

    @TypeConverter
    fun fromSimilarArtistInfoList(list: List<SimilarArtistInfo>?): String? = Gson().toJson(list)

    @TypeConverter
    fun toSimilarArtistInfoList(listString: String?): List<SimilarArtistInfo>? {
        val type = object : TypeToken<List<SimilarArtistInfo>>() {}.type
        return Gson().fromJson(listString, type)
    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray? {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? {
        return if (byteArray != null) {
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        } else {
            null
        }
    }

}
