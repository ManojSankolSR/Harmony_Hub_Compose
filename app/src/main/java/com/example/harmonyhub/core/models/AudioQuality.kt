package com.example.harmonyhub.core.models

import kotlinx.serialization.Serializable


@Serializable

enum class AudioQuality(val kbps: String,val displayName: String) {
    veryLow("12kbps","Very Low (12 Kbps)"),
    low("48kbps","Low (48 Kbps)"),
    medium("96kbps","Medium (96 Kbps)"),
    high("160kbps","High (160 Kbps)"),
    veryHigh("320kbps","Very High (320 Kbps)")
}