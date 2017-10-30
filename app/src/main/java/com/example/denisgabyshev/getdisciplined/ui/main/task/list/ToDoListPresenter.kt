package com.example.denisgabyshev.getdisciplined.ui.main.task.list

import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskPresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by denisgabyshev on 11/10/2017.
 */
class ToDoListPresenter<V : ToDoListMvpView> @Inject constructor(dataManager: DataManager,
                                                                 schedulerProvider: SchedulerProvider,
                                                                 compositeDisposable: CompositeDisposable) :
        BaseTaskPresenter<V>(dataManager, schedulerProvider, compositeDisposable), ToDoListMvpPresenter<V> {

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        mvpView.setFragment()
    }


}