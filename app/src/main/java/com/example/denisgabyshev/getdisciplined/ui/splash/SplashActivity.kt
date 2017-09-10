package com.example.denisgabyshev.getdisciplined.ui.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseActivity

class SplashActivity : BaseActivity(), SplashMvpView {
    override fun openSettingsActivity() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
