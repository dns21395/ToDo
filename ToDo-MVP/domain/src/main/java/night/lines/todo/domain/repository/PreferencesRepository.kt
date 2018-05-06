package night.lines.todo.domain.repository

import io.reactivex.Observable

interface PreferencesRepository {
    fun setFinishedTasksVisibility(status: Boolean)
    fun getFinishedTasksVisibility(): Observable<Boolean>
}