package com.example.harmonyhub.features.auth.data.remote.models

import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.auth.data.local.entity.UserEntity
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val preferredAudioQuality: AudioQuality,
    val preferredLanguage: List<Language>,
){
    companion object{
        fun User.toEntity(): UserEntity {
            return UserEntity(
                id = id,
                name = name,
                preferredAudioQuality = preferredAudioQuality,
                preferredLanguage = preferredLanguage
            )

        }
    }
}