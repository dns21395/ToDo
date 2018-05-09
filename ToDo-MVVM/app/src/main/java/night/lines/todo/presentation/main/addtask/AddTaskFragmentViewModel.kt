package night.lines.todo.presentation.main.addtask

import android.databinding.ObservableField
import android.util.Log
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

    private val TAG = "AddTaskViewModel"

    @Inject lateinit var addTaskUseCase: AddTaskUseCase

    var textTask = ObservableField<String>()

    override fun onViewPrepared() {
        textTask.set("")
    }

    fun onAddTaskButtonClicked() {
        Log.d(TAG, "$textTask")
        when(textTask.get()?.isEmpty()) {
            true -> { navigator?.showToastEmptyText() }
            false -> {
                compositeDisposable.add(
                        addTaskUseCase.execute(Task(0, textTask.get() ?: "", Date().time))
                                .compose(schedulerProvider.ioToMainObservableScheduler())
                                .subscribe {
                                    taskFragmentRelay.callTaskFragmentAction(TaskFragmentRelay.EnumTaskFragment.ITEM_ADDED)
                                    textTask.set("")

                                }
                )
            }
        }
    }
}