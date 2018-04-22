package night.lines.todo.toothpick.task

import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.toothpick.usecase.GetTasksUseCaseProvider
import night.lines.todo.toothpick.usecase.RemoveTaskUseCaseProvider
import night.lines.todo.toothpick.usecase.UpdateTaskUseCaseProvider
import toothpick.config.Module

class TaskModule : Module() {
    init {
        bind(GetTasksUseCase::class.java).toProvider(GetTasksUseCaseProvider::class.java)
        bind(UpdateTaskUseCase::class.java).toProvider(UpdateTaskUseCaseProvider::class.java)
        bind(RemoveTaskUseCase::class.java).toProvider(RemoveTaskUseCaseProvider::class.java)
    }
}