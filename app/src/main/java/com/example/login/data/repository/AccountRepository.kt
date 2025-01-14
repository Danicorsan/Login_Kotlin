package com.example.login.data.repository

import com.example.login.data.model.Account
import com.example.login.data.model.AccountException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object AccountRepository {

    // Simulación de un dataset en memoria
    private val dataSet: MutableList<Account> = mutableListOf()

    init {
        initialize()
    }

    private fun initialize() {
        dataSet.add(
            Account(
                email = "daniel@gmail.com",
                password = "HolaHola19?",
                name = "Daniel",
                surname = "Cortés",
                username = "danics",
                birthdate = "02/03/2003"
            )
        )
        dataSet.add(
            Account(
                email = "lucia@gmail.com",
                password = "Password123!",
                name = "Lucía",
                surname = "García",
                username = "lucygar",
                birthdate = "15/07/1995"
            )
        )
    }

    /**
     * Valida las credenciales de inicio de sesión.
     */
    fun login(email: String, password: String): Result<Account> {
        val account = dataSet.firstOrNull { it.email == email && it.password == password }
        return if (account != null) {
            Result.success(account)
        } else {
            Result.failure(AccountException.NoExistAccount)
        }
    }

    /**
     * Devuelve una lista simulada de cuentas como flujo.
     */
    fun getAccounts(): Flow<List<Account>> = flow {
        delay(2000) // Simula una carga lenta
        emit(dataSet)
    }

    /**
     * Registra una nueva cuenta.
     */
    fun register(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Result<Unit> {
        // Verifica si el email ya está registrado
        if (dataSet.any { it.email == email }) {
            return Result.failure(AccountException.AccountExists)
        }

        // Agrega la nueva cuenta al dataset
        val newAccount = Account(
            email = email,
            password = password,
            name = name,
            surname = surname,
            username = "$name${surname.firstOrNull()}${dataSet.size + 1}",
            birthdate = "01/01/2000" // Fecha ficticia por defecto
        )
        dataSet.add(newAccount)
        return Result.success(Unit)
    }
}

