package night.lines.todo.presentation.main.addtask

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
class AddTaskPresenter @Inject constructor(private val databaseManager: DatabaseManager,
                                           private val schedulerProvider: SchedulerProvider,
                                           private val mainActivityController: MainActivityController
): BasePresenter<AddTaskView>() {

    fun onAddTaskButtonClicked(taskName: String) {
        compositeDisposable.add(
                Observable.fromCallable {
                    databaseManager.insertTask(Task(0, mainActivityController.sectionId, taskName, Date().time))
                }.compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            mainActivityController.callAddTaskFragmentAction(MainActivityController.EnumAddTaskFragment.HIDE)
                        }
        )
    }

    fun onBackPressed() {
        mainActivityController.callAddTaskFragmentAction(MainActivityController.EnumAddTaskFragment.HIDE)
    }
}