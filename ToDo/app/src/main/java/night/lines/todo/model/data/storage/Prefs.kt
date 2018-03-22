package night.lines.todo.model.data.storage

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by denisgabyshev on 21/03/2018.
 */
class Prefs @Inject constructor(context: Context) {

    private val PREF_FILE_NAME = "PREF_DATA"

    private val PREF_KEY_FINISHED_TASKS_VISIBILITY = "PREF_KEY_FINISHED_TASKS_VISIBILITY"

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    fun setFinishedTasksVisibility(status: Boolean) {
        prefs.edit().putBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, status).apply()
    }

    fun getFinishedTasksVisibility(): Boolean =
            prefs.getBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, true)
}