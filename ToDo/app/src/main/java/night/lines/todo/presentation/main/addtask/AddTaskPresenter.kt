package night.lines.todo.presentation.main.addtask

import com.arellomobile.mvp.InjectViewState
import night.lines.todo.database.manager.DatabaseManager
import night.lines.todo.model.system.scheduler.SchedulerProvider
import night.lines.todo.presentation.global.BasePresenter
import night.lines.todo.presentation.global.MainActivityController
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@InjectViewState
class AddTaskPresenter @Inject constructor(private val databaseManager: DatabaseManager,
                                           private val schedulerProvider: SchedulerProvider,
                                           private val mainActivityController: MainActivityController
): BasePresenter<AddTaskView>() {
    fun onAddTaskButtonClicked() {
        mainActivityController.callAddTaskFragmentAction(MainActivityController.EnumAddTaskFragment.HIDE)
    }
}