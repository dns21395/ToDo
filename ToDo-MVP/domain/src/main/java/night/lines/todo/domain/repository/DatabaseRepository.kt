package night.lines.todo.domain.repository

import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.model.TaskID

interface DatabaseRepository {
    fun insertTask(task: Task): Observable<Long>

    fun updateTask(task: Task): Observable<Unit>

    fun removeTask(task: Task): Observable<Unit>

    fun getTasks(showFinished: Boolean, listId: Long): Flowable<ArrayList<Task>>

    fun getTaskIdList(): Flowable<ArrayList<TaskID>>

    fun insertTaskId(taskID: TaskID): Observable<Long>

    fun getTaskIdById(id: Long): Observable<TaskID>
}