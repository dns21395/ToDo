package night.lines.todo.data.prefs

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Observable
import night.lines.todo.domain.repository.PreferencesRepository

class PreferencesRepositoryImpl constructor(context: Context) : PreferencesRepository {
    private val PREF_FILE_NAME = "PREF_DATA"

    private val PREF_KEY_FINISHED_TASKS_VISIBILITY = "PREF_KEY_FINISHED_TASKS_VISIBILITY"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    override fun setFinishedTasksVisibility(status: Boolean) { prefs.edit().putBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, status).apply() }

    override fun getFinishedTasksVisibility(): Observable<Boolean> = Observable.fromCallable { prefs.getBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, false) }
}
