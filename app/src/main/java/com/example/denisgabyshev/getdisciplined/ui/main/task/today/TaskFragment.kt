package com.example.denisgabyshev.getdisciplined.ui.main.task.today

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskFragment
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_tasks_today.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskFragment : BaseTaskFragment(), TaskMvpView {
    private val TAG = "TaskFragment"

    @Inject lateinit var presenter: TaskMvpPresenter<TaskMvpView>
    @Inject lateinit var mainPresenter: MainMvpPresenter<MainMvpView>

    private var isHideToolbarView = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.activityComponent.inject(this)
        presenter.onAttach(this)
    }

    override fun setToolbar(title: Long) {
        collapseToolbar.title = "My Day"
        collapseToolbar.subtitle = AppUtils.makeDate(title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks_today, container, false)

    override fun setFragment() {
        setHasOptionsMenu(true)

        presenter.isTodayExist()

        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = ""

        (activity as MainActivity).addToolbar()

        taskList.layoutManager = LinearLayoutManager(context)
        taskList.adapter = adapter

        fab.setOnClickListener {
            showAddTaskView()
            fab.hide()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home) {
            mainPresenter.onDrawerClick()
        }

        return super.onOptionsItemSelected(item)
    }












}