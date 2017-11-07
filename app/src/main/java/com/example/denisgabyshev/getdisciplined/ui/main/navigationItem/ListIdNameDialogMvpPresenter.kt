package com.example.denisgabyshev.getdisciplined.ui.main.navigationItem

import com.example.denisgabyshev.getdisciplined.data.db.model.ListId
import com.example.denisgabyshev.getdisciplined.ui.base.MvpPresenter

/**
 * Created by denisgabyshev on 05/11/2017.
 */
interface ListIdNameDialogMvpPresenter<V : ListIdNameDialogMvpView> : MvpPresenter<V> {
    fun updateListId(listId: ListId)
}