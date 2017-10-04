package com.example.denisgabyshev.getdisciplined.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import io.reactivex.Flowable

/**
 * Created by denisgabyshev on 18/09/2017.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flowable<List<Task>>

    @Query("SELECT * FROM task WHERE dateId = :date")
    fun getTasksByDayId(date: Long): Flowable<List<Task>>

    @Query("SELECT COUNT(task.id) FROM task "
            + " INNER JOIN date ON date.id = :dateId")
    fun getTaskCount(dateId: Long): Int

    @Insert
    fun insert(task: Task)


}