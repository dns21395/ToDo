package night.lines.todo.data.database.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import night.lines.todo.data.database.db.model.TaskIDModel

@Dao
interface TaskIDDao : BaseDao<TaskIDModel> {
    @Query("SELECT * FROM task_id")
    fun getTaskList(): Flowable<List<TaskIDModel>>
}