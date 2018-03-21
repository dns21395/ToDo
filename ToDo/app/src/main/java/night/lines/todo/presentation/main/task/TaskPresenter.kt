package night.lines.todo.presentation.main.task

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import night.lines.todo.database.manager.DatabaseManager
import night.lines.todo.database.model.Task
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
                                        private val mainActivityController: MainActivityController): BasePresenter<TaskView>() {

    private var array = ArrayList<Task>()

    fun updateTaskArray(array: ArrayList<Task>) {
        this.array = array
    }

    fun getTaskArrayItemCount(): Int = array.size

    fun getTaskByPosition(position: Int): Task = array[position]

    fun onViewPrepared() {
        compositeDisposable.add(
                databaseManager.getAllTasks()
                        .compose(schedulerProvider.ioToMainFlowableScheduler())
                        .subscribe {
                            viewState.updateTaskArray(it as ArrayList<Task>)
                        }
        )
    }

    fun onStatusButtonClick(task: Task) {
        compositeDisposable.add(
                databaseManager.updateTask(task)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe()
        )
    }
}