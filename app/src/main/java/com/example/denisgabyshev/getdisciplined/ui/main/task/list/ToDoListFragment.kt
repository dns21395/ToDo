package com.example.denisgabyshev.getdisciplined.ui.main.task.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskFragment
import kotlinx.android.synthetic.main.fragment_tasks_todo.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 11/10/2017.
 */
class ToDoListFragment : BaseTaskFragment(), ToDoListMvpView {
    private val TAG = "ToDListFragment"

    @Inject lateinit var presenter: ToDoListMvpPresenter<ToDoListMvpView>
    @Inject lateinit var mainPresenter: MainMvpPresenter<MainMvpView>

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.activityComponent.inject(this)
        presenter.onAttach(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks_todo, container, false)

    override fun setToolbar(title: Long) {
        activity.supportActionBar?.title = "My List"
    }

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