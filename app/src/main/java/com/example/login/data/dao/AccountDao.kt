package com.example.login.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.login.data.model.Account
import com.example.login.data.model.AccountException
import kotlinx.coroutines.flow.Flow

/***
 * Account dao
 * contiene los metodos necesarios para el crear seleccionar actualizar y eliminar CRUD
 * objetos de Account en la base de datos Sqlite
 */
@Dao
interface AccountDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("SELECT * FROM Account where id=:accountID")
    suspend fun getAccountById(accountID: Int):Account?

    @Query("SELECT * FROM Account where email=:accountEmail")
    suspend fun getAccountByEmail(accountEmail: String): Account?

    @Query("SELECT * FROM Account order by name ASC")
    fun getAllAccount(): Flow<List<Account>>

    @Query("SELECT * FROM Account WHERE email=:email AND password=:password")
    suspend fun validate(email: String, password:String):Account?
}