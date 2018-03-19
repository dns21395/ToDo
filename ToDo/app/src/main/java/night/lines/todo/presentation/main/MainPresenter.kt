package night.lines.todo.presentation.main

import android.util.Log
import io.reactivex.Observable
import night.lines.todo.database.manager.DatabaseManager
import night.lines.todo.database.model.Task
import night.lines.todo.model.system.scheduler.AppSchedulerProvider
import night.lines.todo.model.system.scheduler.SchedulerProvider
import night.lines.todo.presentation.global.BasePresenter
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class MainPresenter @Inject constructor(private val databaseManager: DatabaseManager,
                                        private val schedulerProvider: SchedulerProvider) : BasePresenter<MainView>() {

    private val TAG = "MainPresenter"

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