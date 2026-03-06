package com.example.harmonyhub.core.models

import com.example.harmonyhub.core.data.local.entity.UserEntity
import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int,
    val name: String,
    val preferredAudioQuality: AudioQuality,
    val preferredLanguage: Language,
){
    companion object{
        fun User.toEntity(): UserEntity{
            return UserEntity(
                id = id,
                name = name,
                preferredAudioQuality = preferredAudioQuality,
                preferredLanguage = preferredLanguage
            )

        }
    }
}
