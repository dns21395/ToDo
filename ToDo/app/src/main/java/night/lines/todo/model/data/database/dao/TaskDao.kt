package night.lines.todo.model.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import night.lines.todo.model.data.database.model.Task

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Dao
interface TaskDao : BaseDao<Task> {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flowable<List<Task>>
}