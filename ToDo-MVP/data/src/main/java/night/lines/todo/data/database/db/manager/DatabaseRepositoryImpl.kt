package night.lines.todo.data.database.db.manager

import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.data.database.db.converter.DatabaseConverter
import night.lines.todo.data.database.db.dao.TaskDao
import night.lines.todo.data.database.db.dao.TaskIDDao
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.model.TaskID
import night.lines.todo.domain.repository.DatabaseRepository

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class DatabaseRepositoryImpl constructor(private val converter: DatabaseConverter,
                                         private val taskDao: TaskDao,
                                         private val taskIDDao: TaskIDDao) : DatabaseRepository {

    override fun getTaskIdList(): Flowable<ArrayList<TaskID>> =
            taskIDDao.getTaskList().map {
                val array = ArrayList<TaskID>()

                for(item in it) {
                    array.add(converter.modelToDomain(item))
                }

                array
            }

    override fun insertTaskId(taskID: TaskID): Observable<Long> =
            Observable.fromCallable { taskIDDao.insert(converter.apiToModel(taskID)) }

    override fun insertTask(task: Task): Observable<Long> =
            Observable.fromCallable { taskDao.insert(converter.apiToModel(task)) }

    override fun updateTask(task: Task): Observable<Unit> =
            Observable.fromCallable { taskDao.update(converter.apiToModel(task))  }

    override fun removeTask(task: Task): Observable<Unit> =
            Observable.fromCallable { taskDao.delete(converter.apiToModel(task)) }

    override fun getTasks(showFinished: Boolean, listId: Long): Flowable<ArrayList<Task>> =
            taskDao.getTasks(if(showFinished) 1 else 0, listId).map {
                val array = ArrayList<Task>()

                for(item in it) {
                    array.add(converter.modelToDomain(item))
                }

                array
            }


}