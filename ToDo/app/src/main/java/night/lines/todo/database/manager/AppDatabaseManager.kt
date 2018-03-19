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
class AppDatabaseManager @Inject constructor(val context: Context,
                                             val commonDao: CommonDao,
                                             val sectionDao: SectionDao,
                                             val taskDao: TaskDao) : DatabaseManager {

    override fun insertTask(task: Task): Long = taskDao.insert(task)

    override fun getAllTasks(): Flowable<List<Task>> = taskDao.getAllTasks()
}