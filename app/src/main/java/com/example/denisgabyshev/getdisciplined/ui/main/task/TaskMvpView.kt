package com.example.denisgabyshev.getdisciplined.ui.main.task

import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.MvpView

/**
 * Created by denisgabyshev on 19/09/2017.
 */
interface TaskMvpView : MvpView {
    fun setToolbar(date: Long)

    fun updateTasksList(array: ArrayList<Task>)

    fun setFragment()

    fun showAddTaskView()

    fun hideAddTaskView()
}