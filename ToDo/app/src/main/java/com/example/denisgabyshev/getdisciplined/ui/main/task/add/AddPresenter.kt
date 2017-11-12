package com.example.denisgabyshev.getdisciplined.ui.main.task.add

import android.util.Log
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * Created by denisgabyshev on 10/10/2017.
 */
class AddPresenter<V: AddMvpView> @Inject
    constructor(dataManager: DataManager,
                schedulerProvider: SchedulerProvider,
                compositeDisposable: CompositeDisposable) :
    BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), AddMvpPresenter<V>{

    private val TAG = "AddPresenter"

    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)

        mvpView.setFragment()
    }

    override fun addTaskToday(taskText: String, operation: () -> Unit) {
        Single.fromCallable {
            dataManager.getDateId(AppUtils.getToday()).subscribe { dateId ->
                dataManager.addTaskToDoAndToday(dateId[0].id, taskText.trim())
            }
        }.subscribeOn(Schedulers.io())
                .subscribe ({operation()}, Throwable::printStackTrace)
    }

    override fun addTaskToDo(taskText: String, operation: () -> Unit) {
        Single.fromCallable {
            dataManager.addTaskToDoAndToday(null, taskText.trim())
        }.subscribeOn(Schedulers.io())
                .subscribe ({operation()}, Throwable::printStackTrace)

    }

    override fun addTaskListId(taskText: String, listId: Long,  operation: () -> Unit) {
        Single.fromCallable {
            dataManager.addTaskList(listId, taskText.trim())
        }.subscribeOn(Schedulers.io())
                .subscribe ({operation()}, Throwable::printStackTrace)
    }



}