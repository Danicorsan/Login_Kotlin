package com.example.login.data.repository

import android.util.Log
import androidx.compose.material3.DatePicker
import com.example.login.data.dao.AccountDao
import com.example.login.data.model.Account
import com.example.login.data.model.AccountException
import com.example.login.data.network.BaseResult
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class AccountRepositoryDB @Inject constructor(
    private val accountDao: AccountDao
) {

    /**
     * Valida las credenciales de inicio de sesión.
     */
    suspend fun validate(email: String, password: String): BaseResult<Account> {
        Log.d("DB_CHECK", "Intentando acceder a la base de datos...")
        val account = accountDao.validate(email, password)

        return if (account != null) {
            Log.d("DB_CHECK", "Acceso exitoso")
            BaseResult.Success(account)
        } else {
            Log.e("DB_CHECK", "Error: Cuenta no encontrada")
            BaseResult.Error(AccountException.NoExistAccount)
        }
    }

    /**
     * Obtiene todas las cuentas como un flujo.
     */
    suspend fun getData(): Flow<List<Account>> {
        return accountDao.getAllAccount()
    }

    /**
     * Registra una nueva cuenta en la base de datos.
     */
    suspend fun register(
        name: String,
        surname: String,
        email: String,
        password: String
    ): Result<Unit> {
        val existingAccount = accountDao.getAccountByEmail(email)
        if (existingAccount != null) {
            return Result.failure(AccountException.AccountExists)
        }
        val newAccount = Account(
            id = 0, // Se generará automáticamente si la base de datos lo permite
            email = email,
            password = password,
            name = name,
            surname = surname,
            username = "$name${surname.firstOrNull()}${System.currentTimeMillis()}",
            birthdate = Date(1,2,3) // Fecha ficticia por defecto
        )
        accountDao.insert(newAccount) // Corrección aquí
        return Result.success(Unit)
    }

    /**
     * Elimina una cuenta de la base de datos.
     */
    suspend fun deleteAccount(account: Account) {
        accountDao.delete(account) // Asegurar que coincide con la firma del DAO
    }
}
