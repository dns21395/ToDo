package com.example.denisgabyshev.getdisciplined.ui.main.navigationItem

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseDialog
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.main.task.list.ListFragment
import kotlinx.android.synthetic.main.dialog_listid_name.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 05/11/2017.
 */
class ListIdNameDialog : BaseDialog(), ListIdNameDialogMvpView {


    val TAG = "ListIdNameDialog"

    @Inject lateinit var presenter: ListIdNameDialogMvpPresenter<ListIdNameDialogMvpView>

    companion object {
        fun newInstance(): ListIdNameDialog {
            val fragment = ListIdNameDialog()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val component = getActivityComponent()

        component.inject(this)

        presenter.onAttach(this)

        positive.setOnClickListener {

            listId()?.name = listIdName.text.toString().trim()
            presenter.updateListId(listId()!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.dialog_listid_name, container, false)

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }

    override fun updateIdListTitleToolbar() {
//        val fragment: ListFragment = (activity.supportFragmentManager.findFragmentById(R.id.frameLayout) as ListFragment)
//        fragment.presenter.getListIdName(activity.currentListId!!.id)

        (activity as MainActivity).clickedNavigationItem(activity.currentListId!!)
    }




}