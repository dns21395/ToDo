package com.example.denisgabyshev.getdisciplined.di.module

import android.app.Application
import android.content.Context
import com.example.denisgabyshev.getdisciplined.data.AppDataManager
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 10/09/2017.
 */
@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

}