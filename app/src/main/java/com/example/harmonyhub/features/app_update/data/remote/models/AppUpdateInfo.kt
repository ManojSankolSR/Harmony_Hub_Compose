package com.example.harmonyhub.features.app_update.data.remote.models

import com.google.gson.annotations.SerializedName

data class AppUpdateInfo(
    @SerializedName("versionCode")
    val versionCode: Int,
    @SerializedName("versionName")
    val versionName: String,
    @SerializedName("apkUrl")
    val apkUrl: String,
    @SerializedName("mandatory")
    val mandatory: Boolean,
    @SerializedName("changelog")
    val changelog: String
)
