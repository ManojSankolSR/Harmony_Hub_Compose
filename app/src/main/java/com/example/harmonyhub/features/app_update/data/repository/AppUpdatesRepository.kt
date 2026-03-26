package com.example.harmonyhub.features.app_update.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.content.FileProvider
import com.example.harmonyhub.core.data.remote.api.ApiService
import com.example.harmonyhub.core.data.remote.api.RetrofitClient
import com.example.harmonyhub.core.services.NetworkService
import com.example.harmonyhub.features.app_update.data.remote.models.AppUpdateInfo
import java.io.File
import java.io.FileOutputStream
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppUpdatesRepository(
    private val networkService: NetworkService,
    private val context: Context
) {

    fun checkInstallPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.packageManager.canRequestPackageInstalls()
        } else {
            true
        }
    }

    fun requestInstallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES).apply {
                data = "package:${context.packageName}".toUri()
            }
            context.startActivity(intent)
        }
    }

    suspend fun checkForUpdates(): AppUpdateInfo {
        if (networkService.isInternetAvailable()) {
            val appUpdateInfo =
                ApiService.appUpdateApi.checkForUpdates(RetrofitClient.CHECK_UPDATES_URL)
            return appUpdateInfo
        } else {
            throw Exception("No Internet Connection")
        }

    }


    private fun createFile(versionName: String): File {
        val dir = context.cacheDir
        if (!dir.exists()) dir.mkdirs()
        return File(dir, "Harmony_Hub_${versionName}.apk")
    }

    suspend fun downloadUpdateAndInstall(
        appUpdateInfo: AppUpdateInfo,
        onProgress: (Int) -> Unit
    ) = withContext(Dispatchers.IO) {
        val file = createFile(appUpdateInfo.versionName)
        val response = ApiService.appUpdateApi.downloadUpdate(appUpdateInfo.apkUrl)

        val body = response.body() ?: throw Exception("Failed to download update")
        val totalBytes = body.contentLength()
        
        body.byteStream().use { input ->
            FileOutputStream(file).use { output ->
                val buffer = ByteArray(8 * 1024)
                var bytesRead: Int
                var totalRead = 0L
                while (input.read(buffer).also { bytesRead = it } != -1) {
                    output.write(buffer, 0, bytesRead)
                    totalRead += bytesRead
                    if (totalBytes > 0) {
                        val progress = ((totalRead * 100) / totalBytes).toInt()
                        onProgress(progress)
                    }
                }
            }
        }

        withContext(Dispatchers.Main) {
            updateApp(file)
        }
    }

    suspend fun updateApp(file: File) {

        val hasInstallPermission=checkInstallPermission()

        if(hasInstallPermission){

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/vnd.android.package-archive")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(intent)

        }else{
            requestInstallPermission()
        }


    }

}
