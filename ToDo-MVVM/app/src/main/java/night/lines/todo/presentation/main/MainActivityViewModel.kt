package night.lines.todo.presentation.main

import android.util.Log
import io.reactivex.Observable
import night.lines.todo.R
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.toothpick.main.Main
import night.lines.todo.ui.base.BaseViewModel
import night.lines.todo.ui.main.ToolbarImages
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

@Main
class MainActivityViewModel @Inject constructor(schedulerProvider: SchedulerProvider,
                                                private val preferencesRepository: PreferencesRepository,
                                                private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                                private val taskFragmentRelay: TaskFragmentRelay)
    : BaseViewModel<MainNavigator>(schedulerProvider) {

    private val TAG = "MainActivityViewModel"

    var bottomFrameLayoutId: Int = 0

    override fun onViewPrepared() {
        Log.d(TAG, "onViewPrepared")

        setToolbarBackground()

        checkFinishedItemsVisibility()

        compositeDisposable.add(
                addTaskFragmentRelay.addTaskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            handleAddTaskFragmentState(it)
                        }
        )
    }

    private fun setToolbarBackground() {
        compositeDisposable.add(
                Observable.fromCallable { ToolbarImages.getBackground() }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { navigator?.setToolbarBackground(it) }
        )
    }


    private fun handleAddTaskFragmentState(enum: AddTaskFragmentRelay.EnumAddTaskFragment) {
        when(enum) {
            AddTaskFragmentRelay.EnumAddTaskFragment.SHOW -> navigator?.showAddTaskFragment()
            else -> navigator?.hideAddTaskFragment()
        }
    }


    private fun checkFinishedItemsVisibility() {
        compositeDisposable.add(
               preferencesRepository.getFinishedTasksVisibility()
                       .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            Log.d(TAG, "setting finished task visibility ${if(it) "show" else "hide"}")
                            updateIconCheckFinishedItemsVisibility(it)
                        }
        )
    }

    private fun updateIconCheckFinishedItemsVisibility(visible: Boolean) {
        Log.d(TAG, "update to ${if(visible) "show" else "hide"}")
        when(visible) {
            true -> navigator?.updateIconCheckFinishedItemsVisibility(R.drawable.check_show)
            false -> navigator?.updateIconCheckFinishedItemsVisibility(R.drawable.check_hide)
        }
    }

    fun setFinishedTasksVisibility() {
        compositeDisposable.add(
                preferencesRepository.getFinishedTasksVisibility()
                        .map {
                            Log.d(TAG, "finishedTaskVisibility $it -> ${!it}")
                            preferencesRepository.setFinishedTasksVisibility(!it)
                        !it
                        }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            Log.d(TAG, "i got <$it> and gonna put it as visibility")
                            updateIconCheckFinishedItemsVisibility(it)
                            taskFragmentRelay.callTaskFragmentAction(TaskFragmentRelay.EnumTaskFragment.FINISHED_ITEMS_VISIBILITY_UPDATED)
                        }
        )
    }

    fun onFabButtonClicked() {
        addTaskFragmentRelay.callAddTaskFragmentAction(AddTaskFragmentRelay.EnumAddTaskFragment.SHOW)
    }

    fun enumAddTaskFragmentHide() {
        addTaskFragmentRelay.callAddTaskFragmentAction(AddTaskFragmentRelay.EnumAddTaskFragment.HIDE)
    }
}