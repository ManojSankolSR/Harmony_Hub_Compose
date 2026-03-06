package com.example.harmonyhub.features.home.data.remote.models

data class MusicDataItem(val explicit: Boolean? = false,
                         val image: Any? = null,
                         val subtitle: String? = "",
                         val name: String? = "",
                         val id: String = "",
                         val type: MusicItemType? = null,
                         val url: String? = "")



fun MusicDataItem.getImageUrl(): String? {
    val url = when (image) {
        is List<*> -> {
            (image.lastOrNull() as? Map<*, *>)?.get("link") as? String
        }
        is String -> image
        else -> null
    }

    return url?.replace("http://", "https://")
}