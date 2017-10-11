package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * Created by denisgabyshev on 11/10/2017.
 */
open class BaseTaskPresenter<V: BaseTaskMvpView> @Inject constructor(dataManager: DataManager,
                                                                schedulerProvider: SchedulerProvider,
                                                                compositeDisposable: CompositeDisposable) :
        BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), BaseTaskMvpPresenter<V> {



    override fun insertToday() {
        doAsync {
            dataManager.addDate(AppUtils.getToday())
        }
    }


    override fun isTodayExist() {
        compositeDisposable?.add(dataManager.getDateId(AppUtils.getToday()).subscribe {
            if(it.isEmpty()) {
                insertToday()
            }
        })
        compositeDisposable?.add(  dataManager.getDateId(AppUtils.getToday())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if(it.isNotEmpty()) {
                        val date = it[0].date
                        val dateId = it[0].id

                        mvpView?.setToolbar(date)
                        getTasksByDate(dateId)
                    }
                })
    }

    override fun getTasksByDate(dateId: Long) {
        compositeDisposable?.add(dataManager.getTasksByDayId(dateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mvpView?.updateTasksList(it as ArrayList<Task>)
                })
    }
}