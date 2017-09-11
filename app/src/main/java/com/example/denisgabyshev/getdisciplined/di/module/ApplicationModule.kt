package com.example.denisgabyshev.getdisciplined.di.module

import android.app.Application
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by denisgabyshev on 10/09/2017.
 */
@Module
class ApplicationModule(private val application: Application) {
    @Provides
    @ApplicationContext
    fun provideContext() = application

    @Provides
    fun provideApplication() = application

}