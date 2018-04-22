package night.lines.todo.presentation.main.addtask

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.model.system.scheduler.SchedulerProvider
import night.lines.todo.presentation.global.BasePresenter
import night.lines.todo.presentation.global.MainActivityController
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@InjectViewState
class AddTaskPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                           private val mainActivityController: MainActivityController
): BasePresenter<AddTaskView>() {

    @Inject lateinit var addTaskUseCase: AddTaskUseCase

    fun onAddTaskButtonClicked(taskName: String) {
        compositeDisposable.add(
                addTaskUseCase.execute(Task(0, taskName, Date().time))
                .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            mainActivityController.callTaskFragmentAction(MainActivityController.EnumTaskFragment.ITEM_ADDED)
                        }
        )
    }
}