package com.example.harmonyhub.features.serach.data.remote.models

data class SearchData(
    val albums: SearchCategory<AlbumSearchItem>? = null,
    val songs: SearchCategory<SongSearchItem>? = null,
    val playlists: SearchCategory<PlaylistSearchItem>? = null,
    val artists: SearchCategory<ArtistSearchItem>? = null,
    val topQuery: SearchCategory<TopQueryItem>? = null,
    val shows: SearchCategory<ShowSearchItem>? = null,
    val episodes: SearchCategory<EpisodeSearchItem>? = null
)