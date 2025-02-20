package com.example.login.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.login.data.converters.Converters
import com.example.login.data.dao.AccountDao
import com.example.login.data.model.Account
import kotlinx.coroutines.*
import java.util.Date

@Database(
    version = 1,
    entities = [Account::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LoginDatabase : RoomDatabase() {
    abstract fun getAccountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDatabase(context: Context): LoginDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LoginDatabase::class.java,
                    "login_database.db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    prepopulateDatabase(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private suspend fun prepopulateDatabase(database: LoginDatabase) {
            val accountDao = database.getAccountDao()
            withContext(Dispatchers.IO) {
                accountDao.insert(
                    Account(
                        id = 1,
                        email = "desdeBaseD@gmail.com",
                        password = "admin",
                        name = "Lucía",
                        surname = "García",
                        username = "lucygar",
                        birthdate = Date(1,2,3)
                    )
                )
                accountDao.insert(
                    Account(
                        id = 2,
                        email = "prueba1@gmail.com",
                        password = "Password123!",
                        name = "Prueba",
                        surname = "Prueba",
                        username = "Prueba",
                        birthdate = Date(1,2,3)
                    )
                )
                accountDao.insert(
                    Account(
                        id = 3,
                        email = "admin@gmail.com",
                        password = "Password123!",
                        name = "Admin",
                        surname = "García",
                        username = "lucygar",
                        birthdate = Date(1,2,3)
                    )
                )
            }
        }
    }
}
