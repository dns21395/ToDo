package com.example.denisgabyshev.getdisciplined.utils

import android.app.Activity
import com.example.denisgabyshev.getdisciplined.App

/**
 * Created by denisgabyshev on 10/09/2017.
 */
val Activity.app: App
    get() = application as App