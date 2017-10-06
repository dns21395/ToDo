package com.example.denisgabyshev.getdisciplined.ui.main.task.add

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R

/**
 * Created by denisgabyshev on 05/10/2017.
 */
class AddFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.task_add_item, container, false)
}