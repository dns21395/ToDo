package com.example.denisgabyshev.getdisciplined.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseActivity
import javax.inject.Inject
import com.example.denisgabyshev.getdisciplined.ui.main.task.TaskFragment
import com.example.denisgabyshev.getdisciplined.ui.main.task.add.AddFragment
import com.example.denisgabyshev.getdisciplined.utils.ScreenUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_header.view.*
import kotlinx.android.synthetic.main.fragment_tasks_today.*


/**
 * Created by denisgabyshev on 18/09/2017.
 */
class MainActivity : BaseActivity(), MainMvpView {
    private val TAG = "MainActivity"

    @Inject lateinit var presenter: MainMvpPresenter<MainMvpView>

    lateinit var drawerToggle: ActionBarDrawerToggle


    companion object {
        fun getStartIntent(context: Context): Intent =
                Intent(context, MainActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.inject(this)

        presenter.onAttach(this)

        setUp()
    }

    override fun setTaskFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TaskFragment()).commitNowAllowingStateLoss()
    }

    override fun setUp() {
        transparentStatusBar()
        setupNavMenu()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    override fun openDrawer() {
        drawerLayout.openDrawer(Gravity.START)
    }

    fun setupNavMenu() {
        navigationView.setPadding(0, ScreenUtils.getStatusBarHeight(this), 0, 0)
        navigationView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            when(it.itemId) {
                R.id.todo -> {
                    showToast("todo")
                    true
                }
                else -> {
                    showToast("default")
                    false
                }
            }
        }
    }

    override fun onBackPressed() {
        val taskFragment: TaskFragment? = supportFragmentManager.findFragmentById(R.id.frameLayout) as TaskFragment

        if(taskFragment?.frameAddTask != null) {
            taskFragment.hideAddTaskView()
        } else {
            super.onBackPressed()
        }

    }




}