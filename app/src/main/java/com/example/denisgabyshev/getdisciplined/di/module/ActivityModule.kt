package com.example.denisgabyshev.getdisciplined.di.module

import android.support.v7.app.AppCompatActivity
import com.example.denisgabyshev.getdisciplined.di.ActivityContext
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by denisgabyshev on 10/09/2017.
 */
@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext() = activity

    @Provides
    fun provideActivity() = activity

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()




}