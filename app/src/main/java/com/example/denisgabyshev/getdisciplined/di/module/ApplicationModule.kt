package com.example.denisgabyshev.getdisciplined.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.denisgabyshev.getdisciplined.App
import com.example.denisgabyshev.getdisciplined.data.AppDataManager
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.AppDatabase
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import com.example.denisgabyshev.getdisciplined.utils.rx.AppSchedulerProvider
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
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

    @Provides
    fun provideAppDatabase(): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "we-need-db").build()

}