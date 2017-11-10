package com.example.denisgabyshev.getdisciplined.di

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by denisgabyshev on 10/09/2017.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PreferenceInfo



