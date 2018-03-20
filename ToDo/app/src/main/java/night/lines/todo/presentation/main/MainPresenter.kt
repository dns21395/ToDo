package night.lines.todo.presentation.main

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import night.lines.todo.database.manager.DatabaseManager
import night.lines.todo.database.model.Task
import night.lines.todo.model.system.scheduler.AppSchedulerProvider
import night.lines.todo.model.system.scheduler.SchedulerProvider
import night.lines.todo.presentation.global.BasePresenter
import night.lines.todo.presentation.global.MainActivityController
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@InjectViewState
class MainPresenter @Inject constructor(private val databaseManager: DatabaseManager,
                                        private val schedulerProvider: SchedulerProvider,
                                        private val mainActivityController: MainActivityController
) : BasePresenter<MainView>() {

    private val TAG = "MainPresenter"

    fun onViewPrepared() {
        compositeDisposable.add(
                mainActivityController.addTaskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            when(it) {
                                MainActivityController.EnumAddTaskFragment.SHOW -> viewState.showAddTaskFragment()
                                else -> viewState.hideAddTaskFragment()
                            }
                        }
        )
    }




    fun onFabButtonClicked() {
        mainActivityController.callAddTaskFragmentAction(MainActivityController.EnumAddTaskFragment.SHOW)
    }

    fun onClickButton(text: String) {
        compositeDisposable.add(
                databaseManager.getAllTasks()
                        .compose(schedulerProvider.ioToMainFlowableScheduler())
                        .subscribe {
                            for(item in it) {
                                Log.d(TAG, "$item")
                            }
                        }
        )
    }


}