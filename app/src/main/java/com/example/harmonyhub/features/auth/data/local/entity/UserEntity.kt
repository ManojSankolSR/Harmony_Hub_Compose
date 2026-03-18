package com.example.harmonyhub.features.auth.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.auth.data.remote.models.User

@Entity(tableName = "user")
data class UserEntity (
    @PrimaryKey
    val id: Int=0,
    val name:String,
    val preferredAudioQuality: AudioQuality,
    val preferredLanguage: List<Language>,

    ){

    companion object{
        fun UserEntity.toUser(): User {
            return User(
                id = id,
                name = name,
                preferredAudioQuality = preferredAudioQuality,
                preferredLanguage = preferredLanguage
            )
        }
    }
}