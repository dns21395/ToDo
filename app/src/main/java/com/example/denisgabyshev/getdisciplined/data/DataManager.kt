package com.example.denisgabyshev.getdisciplined.data

import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import io.reactivex.Flowable


/**
 * Created by denisgabyshev on 11/09/2017.
 */
interface DataManager{
    fun addDate(date: Long)

    fun addTask(dateId: Long, task: String)

    fun getDateId(date: Long): Flowable<List<Date>>

    fun getTasksByDayId(date: Long): Flowable<List<Task>>

    fun updateTaskOrder(task: Task, order: Int)

    fun updateTaskStatus(task: Task)
}