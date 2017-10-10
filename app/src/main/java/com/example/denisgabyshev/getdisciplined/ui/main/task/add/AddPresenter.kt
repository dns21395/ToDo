package com.example.denisgabyshev.getdisciplined.ui.main.task.add

import android.util.Log
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.ui.base.BasePresenter
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import com.example.denisgabyshev.getdisciplined.utils.rx.SchedulerProvider
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

    override fun addTask(taskText: String) {
        if(taskText.isNotEmpty()) {
            doAsync {
                dataManager.getDateId(AppUtils.getToday()).subscribe { dateId ->
                    dataManager.addTask(dateId[0].id, taskText)
                }
            }
        } else {
            mvpView?.showToast("text is empty")
        }
    }
}