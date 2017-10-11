package com.example.denisgabyshev.getdisciplined.ui.main.task.today

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskFragment
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import kotlinx.android.synthetic.main.activity_main_header.view.*
import kotlinx.android.synthetic.main.fragment_tasks_today.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskFragment : BaseTaskFragment(), TaskMvpView, AppBarLayout.OnOffsetChangedListener {


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
        toolbarHeaderView.title.text = "My Day"
        floatHeaderView.title.text = "My Day"

        toolbarHeaderView.subtitle.text = AppUtils.makeDate(title)
        floatHeaderView.subtitle.text = AppUtils.makeDate(title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks_today, container, false)

    override fun setFragment() {
        setHasOptionsMenu(true)

        presenter.isTodayExist()

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