package night.lines.todo.presentation.main.task

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.disposables.Disposable
import night.lines.todo.model.data.database.manager.DatabaseManager
import night.lines.todo.model.data.database.model.Task
import night.lines.todo.model.interactor.task.TaskInteractor
import night.lines.todo.model.system.scheduler.SchedulerProvider
import night.lines.todo.presentation.global.BasePresenter
import night.lines.todo.presentation.global.MainActivityController
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@InjectViewState
class TaskPresenter @Inject constructor(private val databaseManager: DatabaseManager,
                                        private val schedulerProvider: SchedulerProvider,
                                        private val interactor: TaskInteractor,
                                        private val mainController: MainActivityController): BasePresenter<TaskView>() {

    private val TAG = "TaskPresenter"

    private var array = ArrayList<Task>()

    private var getTasksDisposable = updateGetTasksDisposable()

    var isAddTaskFragmentVisible = false

    init {
        onViewPrepared()
    }

    fun updateTaskArray(array: ArrayList<Task>) {
        this.array = array
    }

    fun getTaskArrayItemCount(): Int = array.size

    fun getTaskByPosition(position: Int): Task = array[position]

    fun onViewPrepared() {
        compositeDisposable.add(getTasksDisposable)

        compositeDisposable.add(
                mainController.taskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            when(it) {
                                MainActivityController.EnumTaskFragment.FINISHED_ITEMS_VISIBILITY_UPDATED -> {
                                    Log.d(TAG, "${interactor.getFinishedTasksVisibility()}")
                                    compositeDisposable.remove(getTasksDisposable)
                                    getTasksDisposable = updateGetTasksDisposable()
                                    compositeDisposable.add(getTasksDisposable)
                                }
                                else -> viewState.scrollToEnd()
                            }
                        }
        )

        compositeDisposable.add(
                mainController.addTaskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            isAddTaskFragmentVisible = when(it) {
                                MainActivityController.EnumAddTaskFragment.SHOW -> true
                                else -> false
                            }
                        }
        )
    }

    private fun updateGetTasksDisposable(): Disposable
        = databaseManager.getTasks(interactor.getFinishedTasksVisibility())
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe {
                viewState.updateTaskArray(it as ArrayList<Task>)
            }

    fun onStatusButtonClick(task: Task) {
        compositeDisposable.add(
                databaseManager.updateTask(task)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe()
        )
    }

    fun onItemSwiped(task: Task, function: () -> Unit) {
        compositeDisposable.add(
                databaseManager.removeTask(task)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            function.invoke()
                        }
        )
    }
}