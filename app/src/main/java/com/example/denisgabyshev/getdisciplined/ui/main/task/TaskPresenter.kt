package com.example.denisgabyshev.getdisciplined.ui.main.task

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
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskPresenter<V : TaskMvpView> @Inject constructor(dataManager: DataManager,
                                                         schedulerProvider: SchedulerProvider,
                                                         compositeDisposable: CompositeDisposable) :
        BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), TaskMvpPresenter<V> {


    private val TAG = "TaskPresenter"

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

                        mvpView?.setToolbar(date)
                        getTasksByDate(date)
                    }
                })
    }

    override fun getTasksByDate(dateId: Long) {
        compositeDisposable?.add(dataManager.getTasksByDayId(dateId).subscribe {
            mvpView?.setTasksList(it as ArrayList<Task>)
        })
    }

    override fun addTask() {
        dataManager.addTask(3, "world")
    }










}