package com.example.denisgabyshev.getdisciplined.ui.base

import android.content.Context
import android.support.v7.widget.AppCompatDrawableManager
import android.widget.ImageView
import java.util.*

/**
 * Created by denisgabyshev on 10/09/2017.
 */
interface MvpView {
    fun hideKeyboard()

    fun showToast(message: String)

    fun ImageView.imageBackground(drawable: Int)


}