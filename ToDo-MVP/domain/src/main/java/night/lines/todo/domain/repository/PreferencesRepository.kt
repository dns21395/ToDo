package night.lines.todo.domain.repository

import io.reactivex.Flowable
import io.reactivex.Observable

interface PreferencesRepository {
    fun setFinishedTasksVisibility(status: Boolean)
    fun getFinishedTasksVisibility(): Observable<Boolean>

    fun setTaskListId(taskId: Long): Observable<Unit>
    fun getTaskListId(): Observable<Long>
}