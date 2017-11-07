package com.example.denisgabyshev.getdisciplined.ui.main.navigationItem

import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by denisgabyshev on 05/11/2017.
 */
class ListIdNameDialogPresenter<V : ListIdNameDialogMvpView>
    @Inject constructor(dataManager: DataManager,
                        schedulerProvider: SchedulerProvider?,
                        compositeDisposable: CompositeDisposable?)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), ListIdNameDialogMvpPresenter<V>  {



}