package com.example.harmonyhub.features.storage.data.repository

import android.app.ActivityManager
import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.Process
import android.os.StatFs
import android.os.storage.StorageManager
import androidx.annotation.RequiresApi
import com.example.harmonyhub.features.storage.data.models.DeviceStorageInfo
import java.io.IOException
import java.util.UUID

class StorageRepository(private val context: Context) {

    private val storageManager =
        context.getSystemService(Context.STORAGE_SERVICE) as StorageManager;

    @RequiresApi(Build.VERSION_CODES.O)
    private val storageStatsManager =
        context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager

     fun getDeviceStorageInfo(): DeviceStorageInfo {
        val stat= StatFs(Environment.getDataDirectory().path)

        val blockSize=stat.blockSizeLong;
        val totalBlocks=stat.blockCountLong
        val availableBlocks=stat.availableBlocksLong

        return DeviceStorageInfo(
            totalSpace = blockSize* totalBlocks,
            availableSpace = blockSize * availableBlocks,
            usedSpace = blockSize * (totalBlocks - availableBlocks)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAppUsedStorageSize(): Long {

        val appDir = context.filesDir
        val appDirUUID: UUID = storageManager.getUuidForPath(appDir)
        try {
            val stats = storageStatsManager.queryStatsForUid(appDirUUID, Process.myUid())
            return stats.appBytes + stats.cacheBytes + stats.dataBytes
        } catch (e: IOException) {
            throw Exception(e.message ?: "Some error occurred")
        }
    }

    fun clearAppData() {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.clearApplicationUserData()
    }
}
