package night.lines.todo.presentation.main.navigation

import com.arellomobile.mvp.InjectViewState
import night.lines.todo.R
import night.lines.todo.domain.interactor.main.AddTaskIdUseCase
import night.lines.todo.domain.model.TaskID
import night.lines.todo.presentation.base.BasePresenter
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

@InjectViewState
class TaskIdPresenter @Inject constructor(private val addTaskIdUseCase: AddTaskIdUseCase,
                                          private val schedulerProvider: SchedulerProvider) : BasePresenter<TaskIdView>() {
    fun onPositiveButtonClicked(taskIdName: String) {
        when(taskIdName.isEmpty()) {
            true -> viewState.showToastEditTextEmpty()
            false -> addTaskIdUseCase.execute(TaskID(0, taskIdName))
                    .compose(schedulerProvider.ioToMainObservableScheduler())
                    .subscribe {
                        viewState.showToastListAdded(taskIdName)
                        viewState.dismiss()
                    }
        }

    }
}