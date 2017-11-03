package com.example.denisgabyshev.getdisciplined.ui.main.task.add

import android.os.Bundle
import android.util.Log
import android.view.*
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.base.BaseFragment
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskFragment
import com.example.denisgabyshev.getdisciplined.utils.KeyboardUtils
import kotlinx.android.synthetic.main.task_add_item.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 05/10/2017.
 */
class AddFragment : BaseFragment(), AddMvpView {
    private val TAG = "AddFragment"

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
            addTaskAction()
        }

        KeyboardUtils.showSoftInput(textTask, context)

        textTask.setOnEditorActionListener { textView, i, keyEvent ->
            Log.d(TAG, "$i - $keyEvent")
            if(i == KeyEvent.KEYCODE_ENDCALL) {
                addTaskAction()
            }
            true
        }
    }

    override fun clearEditText() {
        textTask.text.clear()
    }

    override fun addTaskAction() {
        presenter.addTask(textTask.text.toString(), {(parentFragment as BaseTaskFragment).itemInsert()})
    }





}