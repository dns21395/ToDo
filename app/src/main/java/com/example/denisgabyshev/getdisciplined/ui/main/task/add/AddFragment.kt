package com.example.denisgabyshev.getdisciplined.ui.main.task.add

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseFragment
import kotlinx.android.synthetic.main.task_add_item.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 05/10/2017.
 */
class AddFragment : BaseFragment(), AddMvpView {
    @Inject lateinit var presenter: AddMvpPresenter<AddMvpView>

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.activityComponent.inject(this)

        presenter.onAttach(this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.task_add_item, container, false)

    override fun setFragment() {
        addButton.setOnClickListener {
            presenter.addTask(textTask.text.toString())
        }
    }
}