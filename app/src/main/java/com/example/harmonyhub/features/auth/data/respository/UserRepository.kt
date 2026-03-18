package com.example.harmonyhub.features.auth.data.respository

import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.auth.data.local.dao.UserDao
import com.example.harmonyhub.features.auth.data.local.entity.UserEntity.Companion.toUser
import com.example.harmonyhub.features.auth.data.remote.models.User
import com.example.harmonyhub.features.auth.data.remote.models.User.Companion.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val dao: UserDao) {

    fun getUser(): Flow<User?> {
        return dao.getUser().map { entity ->
            entity?.toUser()
        }
    }

    suspend fun createOrUpdateUser(user: User) {
        dao.addOrUpdateUser(user.toEntity())
    }

    suspend fun updateAudioQuality(id: Int, quality: AudioQuality) {
        dao.updateAudioQuality(id, quality)
    }

    suspend fun updatePreferredLanguage(id: Int, languages: List<Language>) {
        dao.updatePreferredLanguage(id, languages)
    }

    suspend fun deleteUser(user: User){
        dao.deleteUser(user.toEntity())
    }
}