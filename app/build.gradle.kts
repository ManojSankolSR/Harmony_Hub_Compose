import java.util.Properties
import java.io.FileInputStream

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(FileInputStream(localPropertiesFile))
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlin.serialization)

    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.harmonyhub"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.harmonyhub"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // No hardcoded URLs here anymore
        buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("BASE_URL", "")}\"")
        buildConfigField("String", "CHECK_UPDATES_URL", "\"${localProperties.getProperty("CHECK_UPDATES_URL", "")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }

    // ✅ ABI splits + universal APK
    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            isUniversalApk = true
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    implementation(libs.androidx.compose.material.icons.extended)


    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)


    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.guava)



    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
// ViewModel utilities for Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
// LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Lifecycle utilities for Compose
    implementation(libs.androidx.lifecycle.runtime.compose)
// Saved state module for ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)


    // Retrofit
    implementation(libs.retrofit)

    // JSON Converter (Gson)
    implementation(libs.converter.gson)

    // OkHttp
    implementation(libs.okhttp)

    // Logging Interceptor (for debugging)
    implementation(libs.logging.interceptor)

    implementation(libs.kotlinx.coroutines.android)


    implementation(libs.coil.compose)

    implementation(libs.coil.network.okhttp)


    // Expo Player(Media 3)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.session)

    implementation(libs.androidx.media3.ui.compose.material3)

    // for ID3 Tags
    implementation(libs.jaudiotagger)


        implementation (libs.compose.charts)



    implementation(libs.waveslider)

    implementation(libs.androidx.palette.ktx)


}