package com.example.harmonyhub.features.playlist.data.remote.models.playlist

data class Artists(val image: Any?,
                   val role: String = "",
                   val name: String = "",
                   val id: String = "",
                   val type: String = "",
                   val url: String = "")

fun Artists.getImageUrl(): String? {
    val url = when (image) {
        is List<*> -> {
            (image.lastOrNull() as? Map<*, *>)?.get("link") as? String
        }

        is String -> image
        else -> null
    }

    return url?.replace("http://", "https://")
}