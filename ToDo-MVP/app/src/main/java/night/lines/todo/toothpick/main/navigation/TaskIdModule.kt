package night.lines.todo.toothpick.main.navigation

import night.lines.todo.domain.interactor.main.AddTaskIdUseCase
import night.lines.todo.toothpick.main.navigation.provider.AddTaskIdUseCaseProvider
import toothpick.config.Module

class TaskIdModule : Module() {
    init {
        bind(AddTaskIdUseCase::class.java).toProvider(AddTaskIdUseCaseProvider::class.java).singletonInScope()
    }
}