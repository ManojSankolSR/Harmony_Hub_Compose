package com.example.harmonyhub.features.playlist.data.remote.models.playlist

data class PlaylistData(val firstname: String = "",
                        val subtitleDesc: List<String>?,
                        val language: String = "",
                        val type: String = "",
                        val followerCount: Int = 0,
                        val headerDesc: String = "",
                        val artists: List<ArtistsItem>?,
                        val share: Int = 0,
                        val id: String = "",
                        val image: String = "",
                        val isDolbyContent: Boolean = false,
                        val lastUpdated: String = "",
                        val listCount: Int = 0,
                        val fanCount: Int = 0,
                        val url: String = "",
                        val modules: Modules,
                        val lastname: String = "",
                        val explicit: Boolean = false,
                        val listType: String = "",
                        val userId: String = "",
                        val songs: List<Song>?,
                        val subtitle: String = "",
                        val name: String = "",
                        val videoCount: Int = 0,
                        val username: String = "")




