package com.example.harmonyhub.features.auth.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.auth.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun addOrUpdateUser(user: UserEntity)

    @Query("UPDATE user SET preferredAudioQuality = :quality WHERE id = :id")
    suspend fun updateAudioQuality(id: Int, quality: AudioQuality)

    @Query("UPDATE user SET preferredLanguage = :languages WHERE id = :id")
    suspend fun updatePreferredLanguage(id: Int, languages: List<Language>)

    @Delete
    suspend fun deleteUser(user: UserEntity)

}