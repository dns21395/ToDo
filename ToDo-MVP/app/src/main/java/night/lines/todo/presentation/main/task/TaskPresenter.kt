package night.lines.todo.presentation.main.task

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.Disposable
import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
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
                                        private val taskFragmentRelay: TaskFragmentRelay): BasePresenter<TaskView>() {

    private val TAG = "TaskPresenter"

    @Inject lateinit var preferencesRepository: PreferencesRepository
    @Inject lateinit var updateTaskUseCase: UpdateTaskUseCase
    @Inject lateinit var removeTaskUseCase: RemoveTaskUseCase
    @Inject lateinit var getTasksUseCase: GetTasksUseCase

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
    }

    private fun isAddTaskFragmentVisible(): Disposable
        = addTaskFragmentRelay.addTaskFragmentState
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe {
                isAddTaskFragmentVisible = when(it) {
                    AddTaskFragmentRelay.EnumAddTaskFragment.SHOW -> {
                        Log.d(TAG, "isAddTaskFragmentVisible : $it")
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
                    TaskFragmentRelay.EnumTaskFragment.FINISHED_ITEMS_VISIBILITY_UPDATED -> {
                        getTasksDisposable.dispose()
                        getTasksDisposable = updateGetTasksDisposable()
                        compositeDisposable.add(getTasksDisposable)
                    }
                    else -> viewState.scrollToEnd()
                }
            }

    private fun updateGetTasksDisposable(): Disposable
         = preferencesRepository.getFinishedTasksVisibility().toFlowable(BackpressureStrategy.BUFFER)
            .flatMap {
                Log.d(TAG, "ipdateGetTasksDisposable() : ${it}")
                getTasksUseCase.execute(it)
            }
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe {
                Log.d(TAG, "$it")
                viewState.updateTaskArray(it)
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