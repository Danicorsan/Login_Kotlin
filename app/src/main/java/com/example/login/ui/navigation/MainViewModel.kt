package com.example.login.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.login.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(MainState(activeAccount = false))
        private set

    fun login(email: String, password: String) {
        val result = AccountRepository.login(email, password) // Acceso manual al `object`
        // Manejar el resultado aquí...
    }
}