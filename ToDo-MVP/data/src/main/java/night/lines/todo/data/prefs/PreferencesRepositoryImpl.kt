package night.lines.todo.data.prefs

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.domain.repository.PreferencesRepository

class PreferencesRepositoryImpl constructor(context: Context) : PreferencesRepository {

    private val PREF_FILE_NAME = "PREF_DATA"

    private val PREF_KEY_FINISHED_TASKS_VISIBILITY = "PREF_KEY_FINISHED_TASKS_VISIBILITY"

    private val PREF_KEY_TASK_LIST_ID = "PREF_KEY_TASK_LIST_ID"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    override fun setFinishedTasksVisibility(status: Boolean) { prefs.edit().putBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, status).apply() }

    override fun getFinishedTasksVisibility(): Observable<Boolean> = Observable.fromCallable { prefs.getBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, false) }

    override fun setTaskListId(taskId: Long): Observable<Unit> =
            Observable.fromCallable { prefs.edit().putLong(PREF_KEY_TASK_LIST_ID, taskId).apply() }

    override fun getTaskListId(): Observable<Long> =
            Observable.fromCallable { prefs.getLong(PREF_KEY_TASK_LIST_ID, 1L) }

}
