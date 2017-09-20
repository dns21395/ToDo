package com.example.denisgabyshev.getdisciplined.data

import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by denisgabyshev on 11/09/2017.
 */
interface DataManager{
    fun addDate(date: Long)

    fun addTask(dateId: Long, task: String)

    fun getDateId(date: Long): Flowable<Int>



}