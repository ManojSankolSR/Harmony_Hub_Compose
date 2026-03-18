package com.example.harmonyhub.core.services

import com.example.harmonyhub.core.models.SnackBar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

object SnackBarManager {

    private val _data= MutableSharedFlow<SnackBar>(
        replay = 0,
        extraBufferCapacity = 1
    )
    val data = _data.asSharedFlow()

    suspend fun show(data: SnackBar){
        _data.emit(data)
    }
}