package night.lines.todo.domain.repository

import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.domain.model.Task

interface DatabaseRepository {
    fun insertTask(task: Task): Observable<Long>

    fun updateTask(task: Task): Observable<Unit>

    fun removeTask(task: Task): Observable<Unit>

    fun getTasks(showFinished: Boolean): Flowable<ArrayList<Task>>
}