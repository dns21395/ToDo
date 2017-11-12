package com.example.denisgabyshev.getdisciplined.ui.main.task.add

import com.example.denisgabyshev.getdisciplined.ui.base.MvpView

/**
 * Created by denisgabyshev on 10/10/2017.
 */
interface AddMvpView: MvpView {
    fun setFragment()

    fun clearEditText()

    fun addTaskAction()
}