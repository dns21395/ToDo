package com.example.denisgabyshev.getdisciplined.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatDrawableManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.example.denisgabyshev.getdisciplined.di.component.ActivityComponent
import com.example.denisgabyshev.getdisciplined.di.component.DaggerActivityComponent
import com.example.denisgabyshev.getdisciplined.di.module.ActivityModule
import com.example.denisgabyshev.getdisciplined.utils.KeyboardUtils
import com.example.denisgabyshev.getdisciplined.utils.app
import org.jetbrains.anko.toast

/**
 * Created by denisgabyshev on 10/09/2017.
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent(app.component())
                .build()
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun hideKeyboard() {
        KeyboardUtils.hideSoftInput(this)
    }

    override fun ImageView.imageBackground(drawable: Int) {
        setImageDrawable(AppCompatDrawableManager.get().getDrawable(context, drawable))
    }

    override fun transparentStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}