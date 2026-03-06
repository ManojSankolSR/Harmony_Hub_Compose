package com.example.harmonyhub.core.data.local.converter

import androidx.room.TypeConverter
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language

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

}
