package night.lines.todo.database.dao

import android.arch.persistence.room.Dao
import night.lines.todo.database.model.Task

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Dao
interface TaskDao : BaseDao<Task> {
}