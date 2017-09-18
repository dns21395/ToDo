package com.example.denisgabyshev.getdisciplined.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.denisgabyshev.getdisciplined.data.db.dao.DateDao
import com.example.denisgabyshev.getdisciplined.data.db.dao.TaskDao
import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import com.example.denisgabyshev.getdisciplined.data.db.model.Task

/**
 * Created by denisgabyshev on 18/09/2017.
 */
@Database(entities = arrayOf(Date::class, Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dateDao(): DateDao
    abstract fun taskDao(): TaskDao
}