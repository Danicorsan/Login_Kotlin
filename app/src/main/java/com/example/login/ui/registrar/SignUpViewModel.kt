package com.example.login.ui.registrar

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposelogincorregido.ui.register.AccountRegisterState
import com.example.login.data.model.AccountException
import com.example.login.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//Inyectar resources
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AccountRepository,
    private val resources:Resources
) : ViewModel() {

    var state by mutableStateOf(AccountRegisterState())
        private set

    fun onNameChange(name: String) {
        state = state.copy(nameUserErrorFormat = null, isNameError = false)

        val nameRegex = "^[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]{2,}$"
        if (!name.matches(Regex(nameRegex))) {
            state = state.copy(
                isNameError = true,
                // Hay que poner esto con strings
                // nameUserErrorFormat = resources.getString()
            )
        }
        state = state.copy(userName = name)
    }

    fun onSurnameChange(surname: String) {
        state = state.copy(userErrorFormat = null, isSurnameError = false)

        if (surname.isBlank()) {
            state = state.copy(
                isSurnameError = true,
                userErrorFormat = "Los apellidos no pueden estar vacíos"
            )
        }
        state = state.copy(userSurname = surname)
    }

    fun onEmailChange(email: String) {
        state = state.copy(emailErrorFormat = null, isEmailError = false)

        val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
        if (!email.matches(Regex(emailRegex))) {
            state = state.copy(
                isEmailError = true,
                emailErrorFormat = "Formato de email incorrecto"
            )
        }
        state = state.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state = state.copy(passwordErrorFormat = null, isPasswordError = false)

        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        if (!password.matches(Regex(passwordRegex))) {
            state = state.copy(
                isPasswordError = true,
                passwordErrorFormat = "Debe tener 8 caracteres, 1 mayúscula, 1 número y 1 especial."
            )
        }
        state = state.copy(password = password)
    }

    fun register(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            if (state.userName.isBlank() || state.userSurname.isBlank()) {
                onError("El nombre y los apellidos no pueden estar vacíos")
                state = state.copy(isLoading = false)
                return@launch
            }

            try {
                repository.register(
                    state.userName,
                    state.userSurname,
                    state.email,
                    state.password
                )
                state = state.copy(success = true, isLoading = false)
                onSuccess()
            } catch (e: AccountException) {
                val message = when (e) {
                    is AccountException.AccountExists -> "Ya existe una cuenta con el email proporcionado"
                    else -> e.message ?: "Error desconocido"
                }
                state = state.copy(serverError = message, isLoading = false)
                onError(message)
            }
        }
    }
}
