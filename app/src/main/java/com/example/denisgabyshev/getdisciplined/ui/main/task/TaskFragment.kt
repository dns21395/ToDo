package com.example.denisgabyshev.getdisciplined.ui.main.task

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.ui.base.BaseFragment
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddFragment
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main_header.view.*
import kotlinx.android.synthetic.main.fragment_tasks_today.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskFragment : BaseFragment(), TaskMvpView, AppBarLayout.OnOffsetChangedListener {


    private val TAG = "TaskFragment"

    @Inject lateinit var presenter: TaskMvpPresenter<TaskMvpView>
    @Inject lateinit var mainPresenter: MainMvpPresenter<MainMvpView>

    private var adapter = TaskAdapter(AppUtils.getToday())

    var frameAddTask: FrameLayout? = null

    private var isHideToolbarView = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.activityComponent.inject(this)
        presenter.onAttach(this)
        presenter.isTodayExist()

        setHasOptionsMenu(true)

        setFragment()
    }

    override fun setToolbar(date: Long) {
        toolbarHeaderView.title.text = "My Day"
        floatHeaderView.title.text = "My Day"

        toolbarHeaderView.subtitle.text = AppUtils.makeDate(date)
        floatHeaderView.subtitle.text = AppUtils.makeDate(date)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks_today, container, false)

    override fun updateTasksList(array: ArrayList<Task>) {
        adapter.setArray(array)
    }

    override fun setFragment() {
        appBar.addOnOffsetChangedListener(this)

        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.title = ""

        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setHomeButtonEnabled(true)

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

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        val maxScroll = appBarLayout.totalScrollRange
        val percentage = Math.abs(offset).toFloat() / maxScroll.toFloat()


        if (percentage == 1f && isHideToolbarView) {
            toolbarHeaderView.visibility = View.VISIBLE
            isHideToolbarView = !isHideToolbarView

        } else if (percentage < 1f && !isHideToolbarView) {
            toolbarHeaderView.visibility = View.GONE
            isHideToolbarView = !isHideToolbarView
        }
    }









}