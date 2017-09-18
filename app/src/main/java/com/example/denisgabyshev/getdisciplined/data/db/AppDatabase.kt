package com.example.denisgabyshev.getdisciplined.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.denisgabyshev.getdisciplined.data.db.dao.DateDao
import com.example.denisgabyshev.getdisciplined.data.db.model.Date

/**
 * Created by denisgabyshev on 18/09/2017.
 */
@Database(entities = arrayOf(Date::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dateDao(): DateDao
}