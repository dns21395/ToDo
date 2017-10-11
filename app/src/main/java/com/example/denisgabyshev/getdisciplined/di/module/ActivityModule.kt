package com.example.denisgabyshev.getdisciplined.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.denisgabyshev.getdisciplined.di.ActivityContext
import com.example.denisgabyshev.getdisciplined.di.PerActivity
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.MainPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.task.today.TaskMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.task.today.TaskMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.today.TaskPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.task.list.ToDoListMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.task.list.ToDoListMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.list.ToDoListPresenter
import com.example.denisgabyshev.getdisciplined.ui.splash.SplashMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.splash.SplashMvpView
import com.example.denisgabyshev.getdisciplined.ui.splash.SplashPresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.AppSchedulerProvider
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by denisgabyshev on 10/09/2017.
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @PerActivity
    fun provideSplashPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> = presenter

    @Provides
    @PerActivity
    fun provideMainPresenter(presenter: MainPresenter<MainMvpView>): MainMvpPresenter<MainMvpView> = presenter

    @Provides
    fun provideTaskMvpPresenter(presenter: TaskPresenter<TaskMvpView>): TaskMvpPresenter<TaskMvpView> = presenter

    @Provides
    fun provideSchedulerManager(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    fun provideAddMvpPresenter(presenter: AddPresenter<AddMvpView>): AddMvpPresenter<AddMvpView> = presenter

    @Provides
    fun provideToDoListMvpPresenter(presenter: ToDoListPresenter<ToDoListMvpView>): ToDoListMvpPresenter<ToDoListMvpView> = presenter
}