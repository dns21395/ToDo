package com.example.denisgabyshev.getdisciplined.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseActivity
import javax.inject.Inject
import com.example.denisgabyshev.getdisciplined.ui.main.task.TaskFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by denisgabyshev on 18/09/2017.
 */
class MainActivity : BaseActivity(), MainMvpView {
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
        setSupportActionBar(toolbar)

        drawerToggle = object : ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            override fun onDrawerOpened(drawerView: View?) {
                super.onDrawerOpened(drawerView)
                hideKeyboard()
            }

            override fun onDrawerClosed(drawerView: View?) {
                super.onDrawerClosed(drawerView)
            }
        }

        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        setupNavMenu()
    }

    fun setupNavMenu() {
        navigationView.setNavigationItemSelectedListener {
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
}