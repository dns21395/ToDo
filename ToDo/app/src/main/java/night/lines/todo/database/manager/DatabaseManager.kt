package night.lines.todo.database.manager

import android.content.Context
import io.reactivex.Flowable
import night.lines.todo.database.dao.CommonDao
import night.lines.todo.database.dao.SectionDao
import night.lines.todo.database.dao.TaskDao
import night.lines.todo.database.model.Task
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
interface DatabaseManager {
    fun insertTask(task: Task): Long

    fun getAllTasks(): Flowable<List<Task>>
}