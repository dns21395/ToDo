package night.lines.todo.toothpick.main.provider

import night.lines.todo.domain.repository.DatabaseRepository
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.presentation.main.MainPresenter
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject
import javax.inject.Provider

class MainPresenterProvider @Inject constructor(private val databaseRepository: DatabaseRepository,
                                                private val preferencesRepository: PreferencesRepository,
                                                private val schedulerProvider: SchedulerProvider,
                                                private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                                private val taskFragmentRelay: TaskFragmentRelay)
    : Provider<MainPresenter> {

    override fun get(): MainPresenter = MainPresenter(databaseRepository, preferencesRepository, schedulerProvider, addTaskFragmentRelay, taskFragmentRelay)
}