package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.BaseFragment
import com.example.denisgabyshev.getdisciplined.ui.base.MvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.TaskAdapter
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddFragment
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_tasks_today.*

/**
 * Created by denisgabyshev on 11/10/2017.
 */
abstract class BaseTaskFragment: BaseFragment(), BaseTaskMvpView {
    var adapter = TaskAdapter(AppUtils.getToday())

    var frameAddTask: FrameLayout? = null

    override fun showAddTaskView() {
        frameAddTask = FrameLayout(context)
        frameAddTask?.id = 1227
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)

        parentTasks.addView(frameAddTask, params)

        val fragment = AddFragment()

        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.addToBackStack("addTask")
        transaction.replace(frameAddTask!!.id, fragment).commit()
    }

    override fun hideAddTaskView() {
        parentTasks.removeView(frameAddTask)
        frameAddTask = null
        fab.show()
    }

    override fun updateTasksList(array: ArrayList<Task>) {
        adapter.setArray(array)
    }
}