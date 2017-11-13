package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import com.example.denisgabyshev.getdisciplined.ui.base.MvpPresenter

/**
 * Created by denisgabyshev on 11/10/2017.
 */
interface BaseTaskMvpPresenter<V: BaseTaskMvpView> : MvpPresenter<V> {
    fun insertToday()

    fun isTodayExist()

    fun getTasks(dateId: Long)

    fun getTasksVisibility()

    fun changeTaskVisibility()
}