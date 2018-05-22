package night.lines.todo.data.database.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.data.database.db.model.TaskModel

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Dao
interface TaskDao : BaseDao<TaskModel> {

    @Query("SELECT * FROM task WHERE (isDone = :finished OR isDone = 0) AND listId = :listId")
    fun getTasks(finished: Int, listId: Long): Flowable<List<TaskModel>>

    @Query("SELECT * FROM task WHERE date = :date AND listId = :listId LIMIT 1")
    fun getTaskByDate(date: Long, listId: Long?): TaskModel
}