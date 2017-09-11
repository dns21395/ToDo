package com.example.denisgabyshev.getdisciplined.ui.splash

import android.os.Bundle
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject lateinit var presenter : SplashMvpPresenter<SplashMvpView>

    override fun openSettingsActivity() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
