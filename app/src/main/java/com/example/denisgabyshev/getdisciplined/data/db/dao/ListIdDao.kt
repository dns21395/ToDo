package com.example.denisgabyshev.getdisciplined.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import com.example.denisgabyshev.getdisciplined.data.db.model.ListId
import io.reactivex.Flowable

/**
 * Created by denisgabyshev on 03/11/2017.
 */
@Dao
interface ListIdDao {
    @Query("SELECT * FROM listId")
    fun getAll(): Flowable<List<ListId>>

    @Query("SELECT * FROM listId ORDER BY id DESC LIMIT 1")
    fun getLastListId(): ListId

    @Query("SELECT COUNT(listId.id) FROM listId")
    fun getListsCount(): Long

    @Insert
    fun insert(list: ListId)

}