package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import android.util.Log
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.channels.actor
import javax.inject.Inject

/**
 * Created by denisgabyshev on 11/10/2017.
 */
open class BaseTaskPresenter<V: BaseTaskMvpView> @Inject constructor(dataManager: DataManager,
                                                                     schedulerProvider: SchedulerProvider,
                                                                     compositeDisposable: CompositeDisposable) :
        BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), BaseTaskMvpPresenter<V> {



    private val TAG = "BaseTaskPresenter"

    var todayId: Long = 0


    override fun insertToday() {
        Observable.just(AppUtils.getToday())
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    dataManager.addDate(it)
                }
    }


    override fun isTodayExist() {
        val getTask = Observable.fromCallable { dataManager . getDateId (AppUtils.getToday()) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    Log.d(TAG, "GET TASK : $it\nIS NOT EMPTY : ${it.isNotEmpty()}")
                    if(it.isNotEmpty()) {
                        Log.d(TAG, "set toolbar etc")
                        val date = it[0].date
                        val dateId = it[0].id
                        mvpView?.setToolbar(date)
                        todayId = dateId
                        getTasks(dateId)
                    }
                }

        val isTodayExist = Observable.fromCallable { dataManager.getDateId(AppUtils.getToday()) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    Log.d(TAG, "IS TODDAY EXIST $it\nIS EMPTY : ${it.isEmpty()}")
                    if (it.isEmpty()) insertToday()
                }

        Observable.merge(isTodayExist, getTask).subscribe()
    }

    override fun getTasks(dateId: Long) {
        dataManager.getTasksByDayId(dateId, dataManager.getFinishedTasksVisibility())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    mvpView?.updateTasksList(it as ArrayList<Task>)
                }, Throwable::printStackTrace)
    }

    override fun getTasksVisibility() {
        mvpView?.updateTasksVisibilityIcon(dataManager.getFinishedTasksVisibility())
    }

    override fun changeTaskVisibility() {
        dataManager.setFinishedTasksVisibility(!dataManager.getFinishedTasksVisibility())
        mvpView?.updateTasksVisibilityIcon(dataManager.getFinishedTasksVisibility())
        (mvpView as BaseTaskFragment).updateTasksArray()
    }
}