package com.example.harmonyhub.core.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.harmonyhub.core.data.local.converter.Converters
import com.example.harmonyhub.core.data.local.dao.UserDao
import com.example.harmonyhub.core.data.local.entity.UserEntity
import com.example.harmonyhub.features.music_player.data.local.dao.PlayerStateDao
import com.example.harmonyhub.features.music_player.data.local.entities.SongEntity
import kotlin.concurrent.Volatile

@Database(entities = [UserEntity::class, SongEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun songDao(): PlayerStateDao

    companion object {

        private const val DATABASE_NAME="app_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null;

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
              val instance=  Room.databaseBuilder(
                  context= context,
                  klass = AppDatabase::class.java,
                  name =  DATABASE_NAME
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}
