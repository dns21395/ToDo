package com.example.denisgabyshev.getdisciplined.data.db.helpers

import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import io.reactivex.Flowable

/**
 * Created by denisgabyshev on 10/11/2017.
 */
interface DateHelper {
    fun addDate(date: Long)

    fun getDateId(date: Long): Flowable<List<Date>>
}