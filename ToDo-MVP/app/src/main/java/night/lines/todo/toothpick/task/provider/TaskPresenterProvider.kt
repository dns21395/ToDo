package night.lines.todo.toothpick.task.provider

import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.presentation.main.task.TaskPresenter
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject
import javax.inject.Provider

class TaskPresenterProvider @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                                private val addTaskFragmentRelay: AddTaskFragmentRelay,
                                                private val taskFragmentRelay: TaskFragmentRelay,
                                                private val preferencesRepository: PreferencesRepository,
                                                private val updateTaskUseCase: UpdateTaskUseCase,
                                                private val removeTaskUseCase: RemoveTaskUseCase,
                                                private val getTasksUseCase: GetTasksUseCase)
    : Provider<TaskPresenter> {
    override fun get(): TaskPresenter = TaskPresenter(schedulerProvider, addTaskFragmentRelay, taskFragmentRelay, preferencesRepository, updateTaskUseCase, removeTaskUseCase, getTasksUseCase)
}