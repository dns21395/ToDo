package com.example.denisgabyshev.getdisciplined.data.db.dao

import android.arch.persistence.room.*
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by denisgabyshev on 18/09/2017.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flowable<List<Task>>

    @Query("SELECT * FROM task WHERE dateId = :date ORDER BY taskOrder")
    fun getTasksByDayId(date: Long): Single<List<Task>>

    @Query("SELECT COUNT(task.id) FROM task "
            + " INNER JOIN date ON date.id = :dateId")
    fun getTaskCount(dateId: Long): Int

    @Update
    fun updateTask(task: Task)

    @Delete
    fun delete(task: Task)

    @Insert
    fun insert(task: Task)


}