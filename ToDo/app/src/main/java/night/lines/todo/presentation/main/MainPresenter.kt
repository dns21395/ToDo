package night.lines.todo.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import night.lines.todo.R
import night.lines.todo.ui.main.MainManager
import night.lines.todo.model.system.scheduler.SchedulerProvider
import night.lines.todo.presentation.global.BasePresenter
import night.lines.todo.presentation.global.MainActivityController
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@InjectViewState
class MainPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                        private val mainActivityController: MainActivityController,
                                        private val interactor: MainManager
) : BasePresenter<MainView>() {

    private val TAG = "MainPresenter"

    var bottomFrameLayoutId: Int = 0

    init {
        onViewPrepared()

        callBackground()

        checkFinishedItemsVisibility()
    }

    private fun onViewPrepared() {
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

    private fun callBackground() {
        compositeDisposable.add(
                interactor.getBackground()
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            viewState.setToolbarBackground(it)
                        }
        )
    }

    private fun checkFinishedItemsVisibility() {
        compositeDisposable.add(
                Observable.fromCallable {
                    interactor.getFinishedTasksVisibility()
                }.compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            updateIconCheckFinishedItemsVisibility(it)
                        }
        )
    }

    private fun updateIconCheckFinishedItemsVisibility(visible: Boolean) {
        when(visible) {
            true -> viewState.updateIconCheckFinishedItemsVisibility(R.drawable.check_show)
            false -> viewState.updateIconCheckFinishedItemsVisibility(R.drawable.check_hide)
        }
    }

    fun setFinishedTasksVisibility() {
        compositeDisposable.add(
                Observable.fromCallable {
                    val visibility = !interactor.getFinishedTasksVisibility()
                    interactor.setFinishedTasksVisibility(visibility)
                    visibility
                }.compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            updateIconCheckFinishedItemsVisibility(it)
                            mainActivityController.callTaskFragmentAction(MainActivityController.EnumTaskFragment.FINISHED_ITEMS_VISIBILITY_UPDATED)
                        }
        )
    }

    fun onFabButtonClicked() {
        mainActivityController.callAddTaskFragmentAction(MainActivityController.EnumAddTaskFragment.SHOW)
    }

    fun enumAddTaskFragmentHide() {
        mainActivityController.callAddTaskFragmentAction(MainActivityController.EnumAddTaskFragment.HIDE)
    }



}