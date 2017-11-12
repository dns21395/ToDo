package com.example.denisgabyshev.getdisciplined.ui.base

import io.reactivex.Observable

/**
 * Created by denisgabyshev on 10/09/2017.
 */
interface MvpPresenter<V : MvpView> {
    fun onAttach(mvpView: V)

    fun onDetach()

}

