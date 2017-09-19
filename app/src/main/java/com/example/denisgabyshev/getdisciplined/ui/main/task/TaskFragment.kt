package com.example.denisgabyshev.getdisciplined.ui.main.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseFragment
import javax.inject.Inject

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskFragment : BaseFragment(), TaskMvpView {

    @Inject lateinit var presenter: TaskMvpPresenter<TaskMvpView>

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.activityComponent.inject(this)

        presenter.onAttach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks, container, false)



}