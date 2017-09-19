package com.example.denisgabyshev.getdisciplined.ui.main.task

import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskPresenter<V : TaskMvpView> @Inject constructor(dataManager: DataManager,
                                                         schedulerProvider: SchedulerProvider,
                                                         compositeDisposable: CompositeDisposable) :
        BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), TaskMvpPresenter<V> {
}