package com.example.denisgabyshev.getdisciplined.utils

import java.util.*

/**
 * Created by denisgabyshev on 18/09/2017.
 */
class AppUtils {
    companion object {
        fun isToday(date: Long): Boolean {
            val userDate = {Calendar.getInstance().time = Date(date)} as Calendar

            val currentDate = {Calendar.getInstance().time = Date()} as Calendar

            return userDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                    userDate.get(Calendar.DAY_OF_YEAR) == currentDate.get(Calendar.DAY_OF_YEAR)
        }
    }
}