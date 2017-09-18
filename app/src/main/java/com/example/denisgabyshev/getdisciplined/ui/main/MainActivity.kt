package com.example.denisgabyshev.getdisciplined.ui.main

import android.os.Bundle
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseActivity
import javax.inject.Inject


/**
 * Created by denisgabyshev on 18/09/2017.
 */
class MainActivity : BaseActivity(), MainMvpView {

    @Inject lateinit var presenter: MainMvpPresenter<MainMvpView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityComponent.inject(this)
    }
}