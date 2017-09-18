package com.example.denisgabyshev.getdisciplined.ui.splash

import android.os.Bundle
import android.view.View
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject lateinit var presenter : SplashMvpPresenter<SplashMvpView>

    override fun openSettingsActivity() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityComponent.inject(this)

        background.imageBackground(R.drawable.splash_background)

        transparentStatusBar()

        splashTitle.setFont("fonts/parisien_night.otf")
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
