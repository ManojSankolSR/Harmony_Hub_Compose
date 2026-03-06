package com.example.harmonyhub.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.harmonyhub.core.data.local.entity.UserEntity
import com.example.harmonyhub.core.models.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Query("SELECT * FROM user LIMIT 1")
     fun getUser(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrUpdateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)




}