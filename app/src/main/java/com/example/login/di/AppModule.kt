package com.example.login.di

import android.content.Context
import android.content.res.Resources
import com.example.login.data.LoginDatabase
import com.example.login.data.dao.AccountDao
import com.example.login.data.repository.AccountRepositoryDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun resources(@ApplicationContext context:Context):Resources{
        return context.resources
    }

    /**
     * Método que provee el DataStore (api-valor) de la sessión
     */

    /*
    @Singleton
    @Provides
    fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { context.preferencesDataStoreFile(Session.DATA) })
    }

     */

    @Singleton
    @Provides
    fun provideLoginDatabase(@ApplicationContext context: Context): LoginDatabase {
        return LoginDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideAccountDao(database: LoginDatabase): AccountDao {
        return database.getAccountDao()
    }

    @Singleton
    @Provides
    fun provideAccountRepository(accountDao: AccountDao): AccountRepositoryDB {
        return AccountRepositoryDB(accountDao)
    }

}