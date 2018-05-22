package night.lines.todo.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import night.lines.todo.R
import night.lines.todo.domain.model.TaskID
import night.lines.todo.domain.repository.DatabaseRepository
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.util.SchedulerProvider
import night.lines.todo.presentation.base.BasePresenter
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.ToolbarImages
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@InjectViewState
class MainPresenter @Inject constructor(private val databaseRepository: DatabaseRepository,
                                        private val preferencesRepository: PreferencesRepository,
                                        private val schedulerProvider: SchedulerProvider,
                                        private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                        private val taskFragmentRelay: TaskFragmentRelay) : BasePresenter<MainView>() {

    private val TAG = "MainPresenter"

    private var array = ArrayList<TaskID>()

    init {
        array.add(TaskID(0, "first"))
        array.add(TaskID(0, "second"))
        array.add(TaskID(0, "third"))

    }

    var bottomFrameLayoutId: Int = 0

    fun onViewPrepared() {
        setToolbarBackground()

        compositeDisposable.add(
                addTaskFragmentRelay.addTaskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            handleAddTaskFragmentState(it)
                            checkFinishedItemsVisibility()
                        }
        )

        viewState?.updateTaskIDArray()
    }

    private fun handleAddTaskFragmentState(enum: AddTaskFragmentRelay.EnumAddTaskFragment) {
        when(enum) {
            AddTaskFragmentRelay.EnumAddTaskFragment.SHOW -> viewState.showAddTaskFragment()
            else -> viewState.hideAddTaskFragment()
        }
    }

    private fun setToolbarBackground() {
        compositeDisposable.add(
                Observable.fromCallable { ToolbarImages.getBackground() }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { viewState.setToolbarBackground(it) }
        )
    }

    private fun checkFinishedItemsVisibility() {
        compositeDisposable.add(
               preferencesRepository.getFinishedTasksVisibility().compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { updateIconCheckFinishedItemsVisibility(it) }
        )
    }

    private fun updateIconCheckFinishedItemsVisibility(visible: Boolean) {
        when(visible) {
            true -> viewState.updateIconCheckFinishedItemsVisibility(R.drawable.check_show)
            false -> viewState.updateIconCheckFinishedItemsVisibility(R.drawable.check_hide)
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

    fun getOnTaskIDList() {
        compositeDisposable.add(
                databaseRepository.getTaskIdList()
                        .compose(schedulerProvider.ioToMainFlowableScheduler())
                        .subscribe {
                            updateTaskIDArray(it)
                        }
        )
    }

    fun updateTaskIDArray(array: ArrayList<TaskID>) {
        this.array = array
        viewState?.updateTaskIDArray()
    }

    fun getTaskIDArrayItemCount(): Int = array.size

    fun getTaskIDByPosition(position: Int): TaskID = array[position]


    fun onFabButtonClicked() {
        addTaskFragmentRelay.callAddTaskFragmentAction(AddTaskFragmentRelay.EnumAddTaskFragment.SHOW)
    }

    fun enumAddTaskFragmentHide() {
        addTaskFragmentRelay.callAddTaskFragmentAction(AddTaskFragmentRelay.EnumAddTaskFragment.HIDE)
    }



}