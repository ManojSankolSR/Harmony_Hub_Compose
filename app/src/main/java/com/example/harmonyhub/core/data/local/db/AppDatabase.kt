package com.example.harmonyhub.core.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.harmonyhub.core.data.local.converter.Converters
import com.example.harmonyhub.core.data.local.dao.UserDao
import com.example.harmonyhub.core.data.local.entity.UserEntity
import com.example.harmonyhub.features.album.data.local.dao.AlbumDao
import com.example.harmonyhub.features.album.data.local.entity.AlbumEntity
import com.example.harmonyhub.features.home.data.local.dao.HomeDao
import com.example.harmonyhub.features.home.data.local.entity.HomeEntity
import com.example.harmonyhub.features.music_player.data.local.dao.PlayerStateDao
import com.example.harmonyhub.features.music_player.data.local.entities.SongEntity
import com.example.harmonyhub.features.playlist.data.local.dao.PlaylistDao
import com.example.harmonyhub.features.playlist.data.local.entity.PlaylistEntity
import kotlin.concurrent.Volatile

@Database(
    entities = [UserEntity::class, SongEntity::class, HomeEntity::class, PlaylistEntity::class, AlbumEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun songDao(): PlayerStateDao

    abstract fun homeDao(): HomeDao

    abstract fun playlistDao(): PlaylistDao

    abstract fun albumDao(): AlbumDao

    companion object {

        private const val DATABASE_NAME = "app_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null;

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context, klass = AppDatabase::class.java, name = DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
