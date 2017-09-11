package com.example.denisgabyshev.getdisciplined.ui.base

/**
 * Created by denisgabyshev on 10/09/2017.
 */
interface MvpPresenter<V : MvpView> {
    fun onAttach(mvpView: V)

    fun onDetach()
}

