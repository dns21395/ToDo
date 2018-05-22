package night.lines.todo.presentation.main.addtask

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.interactor.main.GetTaskListIdUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.util.SchedulerProvider
import night.lines.todo.presentation.base.BasePresenter
import night.lines.todo.ui.main.task.TaskFragmentRelay
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@InjectViewState
class AddTaskPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                           private val taskFragmentRelay: TaskFragmentRelay,
                                           private val getTaskListIdUseCase: GetTaskListIdUseCase)
    : BasePresenter<AddTaskView>() {

    private val TAG = "AddTaskPresenter"

    @Inject lateinit var addTaskUseCase: AddTaskUseCase

    fun onAddTaskButtonClicked(taskName: String) {
        compositeDisposable.add(
                getTaskListIdUseCase.execute()
                        .flatMap {
                            Log.d(TAG, "id : $it")
                            addTaskUseCase.execute(Task(0, it, taskName, Date().time))
                        }
                .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            taskFragmentRelay.callTaskFragmentAction(TaskFragmentRelay.EnumTaskFragment.ITEM_ADDED)
                        }
        )
    }
}