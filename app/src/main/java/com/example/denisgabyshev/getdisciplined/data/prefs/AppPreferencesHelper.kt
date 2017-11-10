package com.example.denisgabyshev.getdisciplined.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.denisgabyshev.getdisciplined.di.ApplicationContext
import com.example.denisgabyshev.getdisciplined.di.PreferenceInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by denisgabyshev on 09/11/2017.
 */

@Singleton
class AppPreferencesHelper @Inject
    constructor(@ApplicationContext val context: Context,
                @PreferenceInfo val prefFileName: String): PreferencesHelper {
    val PREF_KEY_FINISHED_TASKS_VISIBILITY = "PREF_KEY_FINISHED_TASKS_VISIBILITY"

    val prefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun setFinishedTasksVisibility(status: Boolean) {
        prefs.edit().putBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, status).apply()
    }

    override fun getFinishedTasksVisibility(): Boolean =
            prefs.getBoolean(PREF_KEY_FINISHED_TASKS_VISIBILITY, false)
}