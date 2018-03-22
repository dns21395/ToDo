package night.lines.todo.model.data.database.manager

import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.model.data.database.dao.TaskDao
import night.lines.todo.model.data.database.model.Task
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class AppDatabaseManager @Inject constructor(private val taskDao: TaskDao) : DatabaseManager {

    override fun insertTask(task: Task): Long = taskDao.insert(task)

    override fun updateTask(task: Task): Observable<Unit> = Observable.fromCallable { taskDao.update(task) }

    override fun removeTask(task: Task): Observable<Unit> = Observable.fromCallable { taskDao.delete(task) }

    override fun getTasks(showFinished: Int): Flowable<List<Task>> = taskDao.getTasks(showFinished)
}