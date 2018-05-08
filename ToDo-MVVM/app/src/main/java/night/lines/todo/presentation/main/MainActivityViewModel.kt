package night.lines.todo.presentation.main

import io.reactivex.Observable
import night.lines.todo.R
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.ui.base.BaseViewModel
import night.lines.todo.ui.main.ToolbarImages
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(schedulerProvider: SchedulerProvider,
                                                private val preferencesRepository: PreferencesRepository,
                                                private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                                private val taskFragmentRelay: TaskFragmentRelay)
    : BaseViewModel<MainNavigator>(schedulerProvider) {

    var bottomFrameLayoutId: Int = 0

    override fun onViewPrepared() {
        setToolbarBackground()

        compositeDisposable.add(
                addTaskFragmentRelay.addTaskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            handleAddTaskFragmentState(it)
                            checkFinishedItemsVisibility()
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
               preferencesRepository.getFinishedTasksVisibility().compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { updateIconCheckFinishedItemsVisibility(it) }
        )
    }

    private fun updateIconCheckFinishedItemsVisibility(visible: Boolean) {
        when(visible) {
            true -> navigator?.updateIconCheckFinishedItemsVisibility(R.drawable.check_show)
            false -> navigator?.updateIconCheckFinishedItemsVisibility(R.drawable.check_hide)
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

    fun onFabButtonClicked() {
        addTaskFragmentRelay.callAddTaskFragmentAction(AddTaskFragmentRelay.EnumAddTaskFragment.SHOW)
    }

    fun enumAddTaskFragmentHide() {
        addTaskFragmentRelay.callAddTaskFragmentAction(AddTaskFragmentRelay.EnumAddTaskFragment.HIDE)
    }
}