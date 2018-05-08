package night.lines.todo.toothpick.main

import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.presentation.main.MainActivityViewModel
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject
import javax.inject.Provider

class MainActivityViewModelProvider @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                                        private val preferencesRepository: PreferencesRepository,
                                                        private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                                        private val taskFragmentRelay: TaskFragmentRelay)
    : Provider<MainActivityViewModel> {

    override fun get(): MainActivityViewModel = MainActivityViewModel(schedulerProvider, preferencesRepository, addTaskFragmentRelay, taskFragmentRelay)
}