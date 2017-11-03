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
    fun getAllDate(): Flowable<List<ListId>>

    @Insert
    fun insert(list: ListId)
}