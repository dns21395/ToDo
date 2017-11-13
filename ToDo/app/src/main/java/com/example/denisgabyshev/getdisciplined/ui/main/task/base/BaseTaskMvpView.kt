package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.MvpView

/**
 * Created by denisgabyshev on 11/10/2017.
 */
interface BaseTaskMvpView : MvpView {
    fun showAddTaskView()

    fun hideAddTaskView()

    fun updateTasksList(array: ArrayList<Task>)

    fun setToolbar(title: Long)

    fun <V: BaseTaskMvpView> setFragment(presenter: BaseTaskPresenter<V>)

    fun updateTasksVisibilityIcon(visibility: Boolean)
}