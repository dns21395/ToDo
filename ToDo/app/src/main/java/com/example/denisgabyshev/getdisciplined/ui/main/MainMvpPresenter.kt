package com.example.denisgabyshev.getdisciplined.ui.main

import com.example.denisgabyshev.getdisciplined.di.PerActivity
import com.example.denisgabyshev.getdisciplined.ui.base.MvpPresenter

/**
 * Created by denisgabyshev on 18/09/2017.
 */
@PerActivity
interface MainMvpPresenter<V : MainMvpView> : MvpPresenter<V> {
    fun onDrawerClick()

    fun onAddListId()

    fun navigationListIds()


}