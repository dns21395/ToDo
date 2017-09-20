package com.example.denisgabyshev.getdisciplined.utils

import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_tasks.*
import java.util.*

/**
 * Created by denisgabyshev on 18/09/2017.
 */
class AppUtils {
    companion object {
        fun isToday(date: Long): Boolean = getToday() == date

        fun getToday(): Long {
            val day = Date()
            val calendar = Calendar.getInstance()
            calendar.time = day
            return "${calendar.get(Calendar.YEAR)}${calendar.get(Calendar.DAY_OF_YEAR)}".toLong()
        }
    }





}