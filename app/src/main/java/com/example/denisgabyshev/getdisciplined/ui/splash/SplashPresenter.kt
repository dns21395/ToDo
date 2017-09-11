package com.example.denisgabyshev.getdisciplined.ui.splash

import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by denisgabyshev on 11/09/2017.
 */
class SplashPresenter<V : SplashMvpView> @Inject
constructor(dataManager: DataManager?,
            schedulerProvider: SchedulerProvider?,
            compositeDisposable: CompositeDisposable?) :
        BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), SplashMvpPresenter<V>{




}

