package night.lines.todo.ui.main

import android.os.Bundle
import android.view.View
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.widget.FrameLayout
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import night.lines.todo.R
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.ui.base.BaseActivity
import night.lines.todo.ui.main.fragments.addtask.controller.AddTaskFragment
import night.lines.todo.ui.main.fragments.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.fragments.task.controller.TaskFragment
import night.lines.todo.ui.main.fragments.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */

class MainActivity : BaseActivity() {

    private val TAG = "MainActivity"

    @Inject lateinit var compositeDisposable: CompositeDisposable
    @Inject lateinit var schedulerProvider: SchedulerProvider
    @Inject lateinit var addTaskFragmentRelay: AddTaskFragmentRelay
    @Inject lateinit var taskFragmentRelay: TaskFragmentRelay
    @Inject lateinit var preferencesRepository: PreferencesRepository

    private var bottomFrameLayoutId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMenu()

        setToolbarBackground()

        addTaskFragmentState()

        if(bottomFrameLayoutId != 0) createFrameLayout()

        fab.setOnClickListener {
            showAddTaskFragment()
        }

        setFinishedTasksVisibility()

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TaskFragment())
                .commitAllowingStateLoss()
    }

    private fun addTaskFragmentState() {
        compositeDisposable.add(
                addTaskFragmentRelay.addTaskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            when(it) {
                                AddTaskFragmentRelay.EnumAddTaskFragment.SHOW -> showAddTaskFragment()
                                else -> {
                                    hideAddTaskFragment()
                                    checkFinishedItemsVisibility()
                                }
                            }

                        }
        )
    }

    private fun checkFinishedItemsVisibility() {
        compositeDisposable.add(
                preferencesRepository.getFinishedTasksVisibility().compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { updateIconCheckFinishedItemsVisibility(it) }
        )
    }

    private fun createFrameLayout() {
        fab.hide()

        val bottomFrameLayout = FrameLayout(applicationContext)

        val params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        bottomFrameLayout.layoutParams = params

        if(bottomFrameLayoutId == 0) bottomFrameLayoutId = View.generateViewId()

        bottomFrameLayout.id = bottomFrameLayoutId

        parentConstraint.addView(bottomFrameLayout)

        val constraintSet = ConstraintSet()

        constraintSet.clone(parentConstraint)

        constraintSet.connect(bottomFrameLayoutId, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM )

        constraintSet.connect(bottomFrameLayoutId, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)

        constraintSet.connect(bottomFrameLayoutId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        constraintSet.clear(coordinator.id, ConstraintSet.BOTTOM)

        constraintSet.connect(coordinator.id, ConstraintSet.BOTTOM, bottomFrameLayoutId, ConstraintSet.TOP)

        constraintSet.applyTo(parentConstraint)
    }

    private fun showAddTaskFragment() {
        createFrameLayout()

        supportFragmentManager.beginTransaction().replace(bottomFrameLayoutId, AddTaskFragment(), AddTaskFragment.TAG).commit()

        app_bar.setExpanded(false)
    }

    private fun hideAddTaskFragment() {
        val bottomFragment = supportFragmentManager.findFragmentByTag(AddTaskFragment.TAG)

        if(bottomFragment != null) supportFragmentManager.beginTransaction().remove(bottomFragment).commit()

        parentConstraint.removeView(findViewById(bottomFrameLayoutId))

        val constraintSet = ConstraintSet()
        constraintSet.clone(parentConstraint)

        constraintSet.clear(coordinator.id, ConstraintSet.BOTTOM)

        constraintSet.connect(coordinator.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

        constraintSet.applyTo(parentConstraint)

        bottomFrameLayoutId = 0

        fab.show()
    }

    private fun setToolbarBackground() {
        compositeDisposable.add(
                Observable.fromCallable { ToolbarImages.getBackground() }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { toolbarBackground.setImageResource(it) }
        )
    }

    override fun onBackPressed() {
        if(bottomFrameLayoutId != 0) {
            hideAddTaskFragment()
            hideAddTaskFragment()
        }
        else super.onBackPressed()
    }

    private fun setMenu() {
        toolbar.inflateMenu(R.menu.main)

        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.check) setFinishedTasksVisibility()
            false
        }
    }

    private fun updateIconCheckFinishedItemsVisibility(visible: Boolean) {
        when(visible) {
            true -> toolbar.menu.getItem(0).icon = resources.getDrawable(R.drawable.check_show, null)
            false -> toolbar.menu.getItem(0).icon = resources.getDrawable(R.drawable.check_hide, null)
        }
    }

    fun setFinishedTasksVisibility() {
        compositeDisposable.add(
                preferencesRepository.getFinishedTasksVisibility()
                        .map {
                            preferencesRepository.setFinishedTasksVisibility(!it)
                            !it
                        }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            updateIconCheckFinishedItemsVisibility(it)
                            taskFragmentRelay.callTaskFragmentAction(TaskFragmentRelay.EnumTaskFragment.FINISHED_ITEMS_VISIBILITY_UPDATED)
                        }
        )
    }
}