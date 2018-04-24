package night.lines.todo.presentation.main

import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import night.lines.todo.R
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.manager.MainManager
import night.lines.todo.util.SchedulerProvider
import night.lines.todo.presentation.base.BasePresenter
import night.lines.todo.manager.MainActivityController
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

    @Inject lateinit var preferencesRepository: PreferencesRepository

    var bottomFrameLayoutId: Int = 0

    fun onViewPrepared() {
        compositeDisposable.add(
                mainActivityController.addTaskFragmentState
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            handleAddTaskFragmentState(it)
                            callBackground()
                            checkFinishedItemsVisibility()
                        }
        )
    }

    private fun handleAddTaskFragmentState(enum: MainActivityController.EnumAddTaskFragment) {
        when(enum) {
            MainActivityController.EnumAddTaskFragment.SHOW -> viewState.showAddTaskFragment()
            else -> viewState.hideAddTaskFragment()
        }
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
               preferencesRepository.getFinishedTasksVisibility().compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { updateIconCheckFinishedItemsVisibility(it) }
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
                preferencesRepository.getFinishedTasksVisibility()
                        .map {
                            preferencesRepository.setFinishedTasksVisibility(!it)
                        !it
                        }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
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