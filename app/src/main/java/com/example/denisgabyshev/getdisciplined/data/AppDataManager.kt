package com.example.denisgabyshev.getdisciplined.data

import android.content.Context
import com.example.denisgabyshev.getdisciplined.data.db.AppDatabase
import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 11/09/2017.
 */
@Singleton
class AppDataManager @Inject constructor(@ApplicationContext val context: Context, private val database: AppDatabase) : DataManager {
    override fun addDate() {
        val date = Date(0, Date().time.toString())

        Single.fromCallable {
            database.dateDao().insert(date)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun addTask(dateId: Long, task: String) {
        doAsync {
            val count = database.taskDao().getTaskCount(dateId)

            val _task = Task(0, dateId, task, count)

            Single.fromCallable {
                database.taskDao().insert(_task)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()

        }
    }





}