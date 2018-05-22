package night.lines.todo.presentation.main.task

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.Disposable
import night.lines.todo.domain.interactor.main.*
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.util.SchedulerProvider
import night.lines.todo.presentation.base.BasePresenter
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@InjectViewState
class TaskPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                        private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                        private val taskFragmentRelay: TaskFragmentRelay,
                                        private val preferencesRepository: PreferencesRepository,
                                        private val updateTaskUseCase: UpdateTaskUseCase,
                                        private val removeTaskUseCase: RemoveTaskUseCase,
                                        private val getTasksUseCase: GetTasksUseCase,
                                        private val getTaskListIdUseCase: GetTaskListIdUseCase): BasePresenter<TaskView>() {

    private val TAG = "TaskPresenter"

    private var array = ArrayList<Task>()

    private lateinit var getTasksDisposable: Disposable

    var isAddTaskFragmentVisible = false

    fun updateTaskArray(array: ArrayList<Task>) {
        this.array = array
    }

    fun getTaskArrayItemCount(): Int = array.size

    fun getTaskByPosition(position: Int): Task = array[position]

    fun onViewPrepared() {
        getTasksDisposable = updateGetTasksDisposable()

        compositeDisposable.add(getTasksDisposable)

        compositeDisposable.add(showOrHideFinishedItems())

        compositeDisposable.add(isAddTaskFragmentVisible())

        compositeDisposable.add(
                getTaskListIdUseCase.execute()
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            Log.d(TAG, "ID : $it")
                        }
        )
    }

    private fun isAddTaskFragmentVisible(): Disposable
        = addTaskFragmentRelay.addTaskFragmentState
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe {
                isAddTaskFragmentVisible = when(it) {
                    AddTaskFragmentRelay.EnumAddTaskFragment.SHOW -> {
                        viewState.scrollToEnd()
                        true
                    }
                    else -> false
                }
            }

    private fun showOrHideFinishedItems(): Disposable
        = taskFragmentRelay.taskFragmentState
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe {
                when(it) {
                    TaskFragmentRelay.EnumTaskFragment.UPDATE_ARRAY -> {
                        getTasksDisposable.dispose()
                        getTasksDisposable = updateGetTasksDisposable()
                    }
                    else -> viewState.scrollToEnd()
                }
            }

    private fun updateGetTasksDisposable(): Disposable {
        var isFinished = false
        var listId: Long

        return preferencesRepository.getFinishedTasksVisibility().toFlowable(BackpressureStrategy.BUFFER)
                .toObservable()
                .flatMap {
                    isFinished = it
                    getTaskListIdUseCase.execute()
                }
                .toFlowable(BackpressureStrategy.BUFFER)
                .flatMap {
                    listId = it
                    getTasksUseCase.execute(GetTasksUseCaseData(isFinished, listId))
                }
                .compose(schedulerProvider.ioToMainFlowableScheduler())
                .subscribe {
                    Log.d(TAG, "array\n$it")
                    viewState.updateTaskArray(it)
                }

//        return getTasksUseCase.execute(GetTasksUseCaseData(false, null))
//                .compose(schedulerProvider.ioToMainFlowableScheduler())
//                .subscribe {
//                    viewState.updateTaskArray(it)
//                }
    }

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