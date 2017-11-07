package com.example.denisgabyshev.getdisciplined.ui.main.navigationItem

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseDialog
import javax.inject.Inject

/**
 * Created by denisgabyshev on 05/11/2017.
 */
class ListIdNameDialog : BaseDialog(), ListIdNameDialogMvpView {

    private val TAG = "ListIdNameDialog"

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

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.dialog_listid_name, container, false)

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }




}