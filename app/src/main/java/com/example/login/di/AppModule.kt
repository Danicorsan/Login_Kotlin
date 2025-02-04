package com.example.login.di

import android.content.Context
import android.content.res.Resources
import com.example.login.data.repository.AccountRepository
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

    @Provides
    @Singleton
    fun provideAccountRepository(): AccountRepository {
        return AccountRepository
    }
}