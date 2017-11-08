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

    override fun addTask(taskText: String, listId: Long?, operation: () -> Unit) {
        if(taskText.isNotEmpty()) {
            Single.fromCallable {
                if(listId != null) {
                    addTaskByListId(taskText, listId)
                } else {
                    addTaskByDayId(taskText)
                }
                }.subscribeOn(Schedulers.io())
                    .subscribe ({operation()}, Throwable::printStackTrace)
        } else {
            mvpView?.showToast("text is empty")
        }
        mvpView?.clearEditText()
    }

    private fun addTaskByDayId(taskText: String) {
        dataManager.getDateId(AppUtils.getToday()).subscribe { dateId ->
            dataManager.addTask(dateId[0].id, null, taskText.trim())
        }
    }

    private fun addTaskByListId(taskText: String, listId: Long) {
        dataManager.addTask(null, listId, taskText.trim())
    }

}