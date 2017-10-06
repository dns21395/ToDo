package com.example.denisgabyshev.getdisciplined.ui.main.task

import android.app.ActionBar
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.example.denisgabyshev.getdisciplined.App
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.BaseFragment
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddFragment
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tasks.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskFragment : BaseFragment(), TaskMvpView {
    private val TAG = "TaskFragment"

    @Inject lateinit var presenter: TaskMvpPresenter<TaskMvpView>

    private var adapter = TaskAdapter(AppUtils.getToday())
    private var frameAddTask: FrameLayout? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.activityComponent.inject(this)
        presenter.onAttach(this)
        presenter.isTodayExist()
        setFragment()







    }

    override fun setToolbar(date: Long) {
        (activity as MainActivity).setToolbarText(AppUtils.makeDate(date))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks, container, false)

    override fun setTasksList(array: ArrayList<Task>) {
        adapter.setArray(array)
    }

    override fun setFragment() {
        taskList.layoutManager = LinearLayoutManager(context)
        taskList.adapter = adapter

        fab.setOnClickListener {
//            fab.visibility = View.INVISIBLE
//            showAddTaskView()

            val array = ArrayList<Task>()

            (1..10).mapTo(array) { Task(it.toLong(), it.toLong(), "item : $it", 1) }
            setTasksList(array)

        }
    }

    override fun showAddTaskView() {
        frameAddTask = FrameLayout(context)
        frameAddTask?.id = 1227
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)

        parentTasks.addView(frameAddTask, params)

        activity.supportFragmentManager.beginTransaction().replace(frameAddTask!!.id, AddFragment()).commit()
    }

    override fun hideAddTaskView() {
        frameAddTask = null
        fab.visibility = View.VISIBLE
        parentTasks.removeView(frameAddTask)
    }


}