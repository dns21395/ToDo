package com.example.denisgabyshev.getdisciplined.data.db.helpers

import com.example.denisgabyshev.getdisciplined.data.db.model.ListId
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by denisgabyshev on 10/11/2017.
 */
interface ListIdHelper {
    fun addListId()

    fun getLastId() : Single<ListId>

    fun getAllListId(): Flowable<List<ListId>>

    fun updateListId(listId: ListId)

    fun deleteListId(listId: ListId)

    fun getListIdName(id: Long): Single<String>
}