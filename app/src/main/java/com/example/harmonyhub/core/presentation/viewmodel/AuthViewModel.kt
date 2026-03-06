package com.example.harmonyhub.core.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.harmonyhub.core.data.respository.UserRepository
import com.example.harmonyhub.core.models.User
import com.example.harmonyhub.core.models.User.Companion.toEntity
import com.example.harmonyhub.core.presentation.state.AuthUiState
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
        try {
            viewModelScope.launch {
                _uiState.update {
                    it.copy(isLoading = true)
                }
                repository.createOrUpdateUser(user);
            }
        }catch (e:Exception){
            _uiState.update {
                it.copy(isLoading = false, error = e.message)
            }

        }
    }

     fun deleteUser(){
         viewModelScope.launch {
             _uiState.update {
                 it.copy(isLoading = true)
             }
             repository.deleteUser(_uiState.value.user!!);
         }
    }








}


class AuthViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}