package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import android.graphics.Rect
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.BaseFragment
import com.example.denisgabyshev.getdisciplined.ui.base.MvpView
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.TaskAdapter
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddFragment
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_tasks_today.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import javax.inject.Inject

/**
 * Created by denisgabyshev on 11/10/2017.
 */
abstract class BaseTaskFragment: BaseFragment(), BaseTaskMvpView {
    var adapter: TaskAdapter? = null

    var frameAddTask: FrameLayout? = null

    private val TAG = "BaseTaskFragment"

    override fun showAddTaskView() {
        frameAddTask = FrameLayout(context)
        frameAddTask?.id = 1227
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)

        parentTasks.addView(frameAddTask, params)

        val fragment = AddFragment()

        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(frameAddTask!!.id, fragment).commit()
    }

    override fun hideAddTaskView() {
        if(frameAddTask != null) {
            parentTasks.removeView(frameAddTask)
            frameAddTask = null
            fab.show()
        }
    }

    override fun updateTasksList(array: ArrayList<Task>) {
        adapter?.setArray(array)
    }

    override fun setFragment() {
        setHasOptionsMenu(true)

        toolbar.setNavigationIcon(R.drawable.menu)
        toolbar.setNavigationOnClickListener {
            (activity as MainActivity).openDrawer()
        }

        val layoutManager = LinearLayoutManager(context)
        taskList.layoutManager = layoutManager
        adapter = TaskAdapter(layoutManager)
        taskList.adapter = adapter

        fab.setOnClickListener {
            showAddTaskView()
            fab.hide()
        }

        KeyboardVisibilityEvent.setEventListener(activity, {
            if(!it && frameAddTask != null) hideAddTaskView()
        })

    }

}