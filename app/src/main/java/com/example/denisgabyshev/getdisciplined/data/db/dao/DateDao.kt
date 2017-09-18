package com.example.denisgabyshev.getdisciplined.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import io.reactivex.Flowable

/**
 * Created by denisgabyshev on 18/09/2017.
 */
@Dao
interface DateDao {
    @Query("SELECT * FROM date")
    fun getAllDate(): Flowable<List<Date>>

    @Insert
    fun insert(date: Date)
}