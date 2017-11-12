package com.example.denisgabyshev.getdisciplined.di.component

import android.app.Application
import android.content.Context
import com.example.denisgabyshev.getdisciplined.App
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.AppDatabase
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import com.example.denisgabyshev.getdisciplined.di.module.ApplicationModule
import com.example.denisgabyshev.getdisciplined.ui.main.ListAdapter
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.TaskAdapter
import com.example.denisgabyshev.getdisciplined.utils.itemtouch.DragableAdapter
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import dagger.Component
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 10/09/2017.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(taskAdapter: TaskAdapter)
    fun inject(listAdapter: ListAdapter)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager

    fun getAppDatabase(): AppDatabase

}