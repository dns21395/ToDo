package com.example.denisgabyshev.getdisciplined.ui.main.task.today

import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.ui.main.MainActivity
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpPresenter
import com.example.denisgabyshev.getdisciplined.ui.main.MainMvpView
import com.example.denisgabyshev.getdisciplined.ui.main.task.base.BaseTaskFragment
import com.example.denisgabyshev.getdisciplined.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_tasks_today.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import org.jetbrains.anko.appcompat.v7.coroutines.onMenuItemClick
import javax.inject.Inject

/**
 * Created by denisgabyshev on 19/09/2017.
 */
class TaskFragment : BaseTaskFragment(), TaskMvpView {
    private val TAG = "TaskFragment"

    @Inject lateinit var presenter: TaskMvpPresenter<TaskMvpView>

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.activityComponent.inject(this)
        presenter.onAttach(this)
    }

    override fun setToolbar(title: Long) {
        collapseToolbar.title = resources.getString(R.string.myday)
        collapseToolbar.subtitle = AppUtils.makeDate(title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_tasks_today, container, false)

    override fun setFragment() {
        super.setFragment()

        setMenu()

        presenter.isTodayExist()
        presenter.getTasksVisibility()
    }

    private fun setMenu() {
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.check -> {
                    presenter.changeTaskVisibility()
                }
            }
            false
        }
    }

    override fun updateTasksArray() {
        presenter.isTodayExist()
    }
}