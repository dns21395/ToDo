package com.example.denisgabyshev.getdisciplined.ui.main

import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/09/2017.
 */
class MainPresenter<V : MainMvpView> @Inject
constructor(dataManager: DataManager,
            schedulerProvider: SchedulerProvider?,
            compositeDisposable: CompositeDisposable?) :
        BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MainMvpPresenter<V>{
    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)

        mvpView.setTaskFragment()
    }
}