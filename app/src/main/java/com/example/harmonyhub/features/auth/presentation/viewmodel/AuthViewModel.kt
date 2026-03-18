package com.example.harmonyhub.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.features.auth.data.respository.UserRepository
import com.example.harmonyhub.core.models.AudioQuality
import com.example.harmonyhub.core.models.Language
import com.example.harmonyhub.features.auth.data.remote.models.User
import com.example.harmonyhub.features.auth.presentation.state.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository): ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(
        AuthUiState(
            user = null,
            isLoading = true,
            error = null
        )
    );

    val state=_uiState.asStateFlow();

    init {
        observeUser();
    }

    private fun observeUser(){
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            repository.getUser().collect { user->
                _uiState.update {
                    it.copy(user = user, isLoading = false)
                }
            }
        }
    }

    fun addOrUpdateUser(user: User){
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                repository.createOrUpdateUser(user);
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    fun updateAudioQuality(quality: AudioQuality) {
        val user = _uiState.value.user ?: return
        viewModelScope.launch {
            try {
                repository.updateAudioQuality(user.id, quality)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message)
                }
            }
        }
    }

    fun updatePreferredLanguage(languages: List<Language>) {
        val user = _uiState.value.user ?: return
        viewModelScope.launch {
            try {
                repository.updatePreferredLanguage(user.id, languages)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message)
                }
            }
        }
    }

     fun deleteUser(){
         viewModelScope.launch {
             _uiState.update {
                 it.copy(isLoading = true)
             }
             _uiState.value.user?.let {
                 repository.deleteUser(it)
             }
         }
    }
}
