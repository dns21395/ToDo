package night.lines.todo.presentation.main

import io.reactivex.Observable
import night.lines.todo.ui.base.BaseViewModel
import night.lines.todo.ui.main.ToolbarImages
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(schedulerProvider: SchedulerProvider) : BaseViewModel<MainNavigator>(schedulerProvider) {
    override fun onViewPrepared() {
        setToolbarBackground()
    }

    private fun setToolbarBackground() {
        compositeDisposable.add(
                Observable.fromCallable { ToolbarImages.getBackground() }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe { navigator?.setToolbarBackground(it) }
        )
    }
}