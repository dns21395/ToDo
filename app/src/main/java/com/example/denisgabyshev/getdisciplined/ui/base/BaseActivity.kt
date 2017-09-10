package com.example.denisgabyshev.getdisciplined.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.example.denisgabyshev.getdisciplined.utils.KeyboardUtils
import org.jetbrains.anko.toast

/**
 * Created by denisgabyshev on 10/09/2017.
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun hideKeyboard() {
        KeyboardUtils.hideSoftInput(this)
    }
}