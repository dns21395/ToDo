package night.lines.todo.toothpick.main

import night.lines.todo.presentation.main.MainActivityViewModel
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject
import javax.inject.Provider

class MainActivityViewModelProvider @Inject constructor(private val schedulerProvider: SchedulerProvider) : Provider<MainActivityViewModel> {
    override fun get(): MainActivityViewModel = MainActivityViewModel(schedulerProvider)
}