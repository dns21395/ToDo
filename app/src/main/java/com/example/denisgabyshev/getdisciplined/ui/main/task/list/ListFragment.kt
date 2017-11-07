package com.example.denisgabyshev.getdisciplined.ui.main.task.list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskFragment
import kotlinx.android.synthetic.main.dialog_listid_name.view.*
import kotlinx.android.synthetic.main.fragment_tasks_todo.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 03/11/2017.
 */
class ListFragment : BaseTaskFragment(), ListMvpView {

    private val TAG = "ListFragment"

    @Inject lateinit var presenter: ListMvpPresenter<ListMvpView>

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity.activityComponent.inject(this)
        presenter.onAttach(this)

        setFragment()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks_todo, container, false)



    override fun setToolbar(title: Long) {

    }

    override fun setToolbar(text: String) {
        toolbar.title = text
    }

    override fun itemInsert() {

    }

    override fun setFragment() {
        super.setFragment()
        presenter.getListIdName(activity.currentListId!!.id)
    }
}