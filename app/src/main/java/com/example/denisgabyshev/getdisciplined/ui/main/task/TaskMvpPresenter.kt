package com.example.denisgabyshev.getdisciplined.ui.main.task

import com.example.denisgabyshev.getdisciplined.ui.base.MvpPresenter

/**
 * Created by denisgabyshev on 19/09/2017.
 */
interface TaskMvpPresenter<V : TaskMvpView> : MvpPresenter<V> {
    fun onButtonClick()

    fun insertToday()
}