package com.example.harmonyhub.core.data.respository

import android.content.Context
import com.example.harmonyhub.core.data.local.dao.UserDao
import com.example.harmonyhub.core.data.local.db.AppDatabase
import com.example.harmonyhub.core.data.local.entity.UserEntity
import com.example.harmonyhub.core.data.local.entity.UserEntity.Companion.toUser
import com.example.harmonyhub.core.models.User
import com.example.harmonyhub.core.models.User.Companion.toEntity
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

    suspend fun deleteUser(user: User){
        dao.deleteUser(user.toEntity())
    }
}