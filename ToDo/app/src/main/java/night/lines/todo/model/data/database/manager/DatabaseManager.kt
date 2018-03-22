package night.lines.todo.model.data.database.manager

import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.model.data.database.model.Task

/**
 * Created by denisgabyshev on 18/03/2018.
 */
interface DatabaseManager {
    fun insertTask(task: Task): Long

    fun getAllTasks(): Flowable<List<Task>>

    fun updateTask(task: Task): Observable<Unit>

    fun removeTask(task: Task): Observable<Unit>
}