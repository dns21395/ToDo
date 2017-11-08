package com.example.denisgabyshev.getdisciplined.data

import android.content.Context
import android.util.Log
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.db.AppDatabase
import com.example.denisgabyshev.getdisciplined.data.db.model.Date
import com.example.denisgabyshev.getdisciplined.data.db.model.ListId
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 11/09/2017.
 */
@Singleton
class AppDataManager @Inject constructor(@ApplicationContext val context: Context,
                                         private val database: AppDatabase) : DataManager {
    override fun getListIdName(id: Long): Single<String> =
            database.listIdDao().getListIdName(id)

    private val TAG = "AppDataManager"

    override fun addListId() {
        val count = database.listIdDao().getListsCount()

        val name = context.resources.getString(R.string.untitled_list)

        Single.fromCallable {
            database.listIdDao().insert(ListId(0, name, count))
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun updateListId(listId: ListId) {
        Single.fromCallable {
            database.listIdDao().updateListId(listId)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun deleteListId(listId: ListId) {
        Single.fromCallable {
            database.listIdDao().deleteListId(listId)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun getAllListId(): Flowable<List<ListId>> =
        database.listIdDao().getAll()

    override fun getAllTasks(): Flowable<List<Task>> =
        database.taskDao().getAllTasks()



    override fun getLastId() : Single<ListId> =
            database.listIdDao().getLastListId()



    override fun addDate(date: Long) {
        val _date = Date(0, date)

        Single.fromCallable {
            database.dateDao().insert(_date)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }


    override fun addTask(dateId: Long?, listId: Long?, task: String) {

        Log.d(TAG, "dateId : $dateId; listId : $listId")

        var count = 0
        if(dateId != null) count = database.taskDao().getTaskCountByDayId(dateId)
        if(listId != null) count = database.taskDao().getTaskCountByListId(listId)


        Log.d(TAG, "count : $count")

        val _task = Task(0, dateId, listId, task, count)

        Single.fromCallable {
            database.taskDao().insert(_task)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

     override fun getDateId(date: Long): Flowable<List<Date>> =
                database.dateDao().getDateId(date)


    override fun getTasksByDayId(date: Long): Single<List<Task>> =
            database.taskDao().getTasksByDayId(date)

    override fun getTasksByListId(id: Long): Single<List<Task>> =
            database.taskDao().getTasksByListId(id)

    override fun updateTaskOrder(task: Task, order: Int) {
        task.taskOrder = order

        Single.fromCallable {
            database.taskDao().updateTask(task)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun updateTaskStatus(task: Task) {
        Single.fromCallable {
            database.taskDao().updateTask(task)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun deleteTask(task: Task) {
        Single.fromCallable {
            database.taskDao().delete(task)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun getTasksToDo(): Single<List<Task>> =
        database.taskDao().getTasksToDo()




}