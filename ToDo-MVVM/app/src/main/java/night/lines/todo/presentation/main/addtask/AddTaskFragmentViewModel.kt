package night.lines.todo.presentation.main.addtask

import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.util.SchedulerProvider
import night.lines.todo.toothpick.main.Main
import night.lines.todo.ui.base.BaseViewModel
import night.lines.todo.ui.main.task.TaskFragmentRelay
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@Main
class AddTaskFragmentViewModel @Inject constructor(schedulerProvider: SchedulerProvider,
                                                   private val taskFragmentRelay: TaskFragmentRelay)
    : BaseViewModel<AddTaskNavigator>(schedulerProvider) {

    @Inject lateinit var addTaskUseCase: AddTaskUseCase

    override fun onViewPrepared() {}

    fun onAddTaskButtonClicked(taskName: String) {
        compositeDisposable.add(
                addTaskUseCase.execute(Task(0, taskName, Date().time))
                .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            taskFragmentRelay.callTaskFragmentAction(TaskFragmentRelay.EnumTaskFragment.ITEM_ADDED)
                        }
        )
    }
}