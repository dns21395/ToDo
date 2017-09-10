package com.example.denisgabyshev.getdisciplined.ui.base

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Created by denisgabyshev on 10/09/2017.
 */
class BaseFragment : Fragment(), MvpView {
    lateinit var activity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is BaseActivity) {
            activity = context
        }
    }

    override fun hideKeyboard() {

    }

    override fun showToast(message: String) {

    }
}