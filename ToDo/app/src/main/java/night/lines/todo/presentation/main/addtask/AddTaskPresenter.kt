package night.lines.todo.presentation.main.addtask

import com.arellomobile.mvp.InjectViewState
import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.util.SchedulerProvider
import night.lines.todo.presentation.base.BasePresenter
import night.lines.todo.ui.main.MainNavigationRelay
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@InjectViewState
class AddTaskPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                           private val mainActivityController: MainNavigationRelay
): BasePresenter<AddTaskView>() {

    @Inject lateinit var addTaskUseCase: AddTaskUseCase

    fun onAddTaskButtonClicked(taskName: String) {
        compositeDisposable.add(
                addTaskUseCase.execute(Task(0, taskName, Date().time))
                .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            mainActivityController.callTaskFragmentAction(MainNavigationRelay.EnumTaskFragment.ITEM_ADDED)
                        }
        )
    }
}