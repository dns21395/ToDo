package com.example.denisgabyshev.getdisciplined.di.component

import com.example.denisgabyshev.getdisciplined.di.PerActivity
import com.example.denisgabyshev.getdisciplined.di.module.ActivityModule
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.splash.SplashActivity
import dagger.Component

/**
 * Created by denisgabyshev on 10/09/2017.
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(mainActivity: MainActivity)
}