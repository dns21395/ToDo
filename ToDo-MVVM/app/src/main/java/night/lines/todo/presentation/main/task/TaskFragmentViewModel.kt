package night.lines.todo.presentation.main.task

import android.util.Log
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.Disposable
import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.toothpick.main.Main
import night.lines.todo.ui.base.BaseViewModel
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

@Main
class TaskFragmentViewModel @Inject constructor(schedulerProvider: SchedulerProvider,
                                                private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                                private val taskFragmentRelay: TaskFragmentRelay) : BaseViewModel<TaskNavigator>(schedulerProvider) {

    @Inject lateinit var preferencesRepository: PreferencesRepository
    @Inject lateinit var updateTaskUseCase: UpdateTaskUseCase
    @Inject lateinit var removeTaskUseCase: RemoveTaskUseCase
    @Inject lateinit var getTasksUseCase: GetTasksUseCase

    private val TAG = "TaskFragmentViewModel"

    private var array = ArrayList<Task>()

    private lateinit var getTasksDisposable: Disposable

    var isAddTaskFragmentVisible = false

    fun updateTaskArray(array: ArrayList<Task>) {
        this.array = array
    }

    fun getTaskArrayItemCount(): Int = array.size

    fun getTaskByPosition(position: Int): Task = array[position]

    override fun onViewPrepared() {
        getTasksDisposable = updateGetTasksDisposable()

        compositeDisposable.add(getTasksDisposable)

        compositeDisposable.add(taskFragmentState())

        compositeDisposable.add(isAddTaskFragmentVisible())
    }

    private fun isAddTaskFragmentVisible(): Disposable
            = addTaskFragmentRelay.addTaskFragmentState
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe {
                isAddTaskFragmentVisible = when(it) {
                    AddTaskFragmentRelay.EnumAddTaskFragment.SHOW -> {
                        navigator?.scrollToEnd()
                        true
                    }
                    else -> false
                }
            }

    private fun taskFragmentState(): Disposable
            = taskFragmentRelay.taskFragmentState
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe {
                when(it) {
                    TaskFragmentRelay.EnumTaskFragment.FINISHED_ITEMS_VISIBILITY_UPDATED -> {

                    }
                    else -> navigator?.scrollToEnd()
                }
            }

    private fun updateGetTasksDisposable(): Disposable
    = preferencesRepository.getFinishedTasksVisibility()
            .toFlowable(BackpressureStrategy.BUFFER)
            .flatMap { getTasksUseCase.execute(it) }
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe ({
                Log.d(TAG, "onNext : $it")
                navigator?.updateTaskArray(it)}, {
                Log.e(TAG, "onError : $it")
            }, {
                Log.d(TAG, "onComplete")
            })

    fun onStatusButtonClick(task: Task) {
        compositeDisposable.add(
                updateTaskUseCase.execute(task)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe()
        )
    }

    fun onItemSwiped(task: Task, function: () -> Unit) {
        compositeDisposable.add(
                removeTaskUseCase.execute(task)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            function.invoke()
                        }
        )
    }
}