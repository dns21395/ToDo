package com.example.denisgabyshev.getdisciplined

import android.app.Application
import android.arch.persistence.room.Room
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.AppDatabase
import com.example.denisgabyshev.getdisciplined.di.component.ApplicationComponent
import com.example.denisgabyshev.getdisciplined.di.component.DaggerApplicationComponent
import com.example.denisgabyshev.getdisciplined.di.module.ApplicationModule
import javax.inject.Inject

/**
 * Created by denisgabyshev on 10/09/2017.
 */
class App : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    @Inject lateinit var dataManager: DataManager

     private val applicationComponent: ApplicationComponent by lazy {
         DaggerApplicationComponent
                 .builder()
                 .applicationModule(ApplicationModule(this))
                 .build()
     }

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)

        App.database = Room.databaseBuilder(this, AppDatabase::class.java, "we-need-db").build()
    }

    fun component() = applicationComponent
}