package com.example.denisgabyshev.getdisciplined.ui.main.task.list

import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.task.today.TaskMvpPresenter
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by denisgabyshev on 03/11/2017.
 */
class ListPresenter<V: ListMvpView>
    @Inject constructor(dataManager: DataManager,
                        schedulerProvider: SchedulerProvider,
                        compositeDisposable: CompositeDisposable) :
    BaseTaskPresenter<V>(dataManager, schedulerProvider, compositeDisposable), ListMvpPresenter<V> {

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        mvpView.setFragment()
    }

    override fun getTasksByDate(dateId: Long) {
        dataManager.getTasksByDayId(dateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    mvpView?.updateTasksList(it as ArrayList<Task>)
                }, Throwable::printStackTrace)
    }
}