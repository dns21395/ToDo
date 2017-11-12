package com.example.denisgabyshev.getdisciplined.data.prefs

/**
 * Created by denisgabyshev on 09/11/2017.
 */
interface PreferencesHelper {
    fun setFinishedTasksVisibility(status: Boolean)

    fun getFinishedTasksVisibility(): Boolean
}