package com.example.denisgabyshev.getdisciplined.ui.main.task.list

import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskMvpPresenter

/**
 * Created by denisgabyshev on 03/11/2017.
 */
interface ListMvpPresenter<V : ListMvpView> : BaseTaskMvpPresenter<V> {
    fun getListIdName(id: Long)


}