package com.example.denisgabyshev.getdisciplined.ui.main

import android.support.v4.app.Fragment
import com.example.denisgabyshev.getdisciplined.data.db.model.ListId
import com.example.denisgabyshev.getdisciplined.ui.base.MvpView

/**
 * Created by denisgabyshev on 18/09/2017.
 */
interface MainMvpView : MvpView {
    fun setTaskFragment(fragment: Fragment)

    fun openDrawer()

    fun closeDrawer()

    fun updateNavigationArray(array: ArrayList<ListId>)

    fun showListIdNameDialog()

}