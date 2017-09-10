package com.example.denisgabyshev.getdisciplined.di.module

import android.support.v7.app.AppCompatActivity
import com.example.denisgabyshev.getdisciplined.di.ActivityContext
import com.example.denisgabyshev.getdisciplined.di.PerActivity
import com.example.denisgabyshev.getdisciplined.ui.splash.SplashMvpInteractor
import com.example.denisgabyshev.getdisciplined.ui.splash.SplashMvpView
import com.example.denisgabyshev.getdisciplined.ui.splash.SplashPresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.AppSchedulerProvider
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

    @Provides
    fun provideSchedulerProvider() = AppSchedulerProvider()

    @Provides
    @PerActivity
    fun provideSplashPresenter(presenter: SplashPresenter<SplashMvpView, SplashMvpInteractor>) = presenter



}