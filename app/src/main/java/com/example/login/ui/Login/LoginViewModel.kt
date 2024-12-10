package com.example.login.ui.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.login.data.repository.AccountRepository
import kotlinx.coroutines.launch

//no lo uso porque no me funcionaba
class LoginViewModel : ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(email: String) {
        state = state.copy(emailErrorFormat = null, isEmailError = false)

        if (email.contains(' ')) return

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

        if (password.contains(' ')) return

        val passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        if (!password.matches(Regex(passwordRegex))) {
            state = state.copy(
                isPasswordError = true,
                passwordErrorFormat = "Debe tener 8 caracteres, 1 mayúscula, 1 número y 1 especial."
            )
        }
        state = state.copy(password = password)
    }

    fun onLoginClick(context: Context) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = AccountRepository.login(state.email, state.password)
            if (result.isSuccess) {
                state = state.copy(success = true, isLoading = false)
                Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
            } else {
                state = state.copy(userError = "Credenciales incorrectas", isLoading = false)
                Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onRegisterClick(context: Context) {
        Toast.makeText(context, "Registrandote...", Toast.LENGTH_SHORT).show()
    }
}