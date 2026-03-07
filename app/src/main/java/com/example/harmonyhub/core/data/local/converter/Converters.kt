package com.example.harmonyhub.core.data.local.converter

import androidx.room.TypeConverter
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.ArtistMap
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.DownloadUrlItem
import com.example.harmonyhub.features.playlist.data.remote.models.playlist.Rights
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

}
