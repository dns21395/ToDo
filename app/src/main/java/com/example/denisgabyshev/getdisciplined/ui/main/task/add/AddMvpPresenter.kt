package com.example.denisgabyshev.getdisciplined.ui.main.task.add

import com.example.denisgabyshev.getdisciplined.ui.base.MvpPresenter

/**
 * Created by denisgabyshev on 10/10/2017.
 */
interface AddMvpPresenter<V: AddMvpView> : MvpPresenter<V> {
    fun addTask(taskText: String)
}