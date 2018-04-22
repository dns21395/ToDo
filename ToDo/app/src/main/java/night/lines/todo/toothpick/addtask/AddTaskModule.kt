package night.lines.todo.toothpick.addtask

import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.toothpick.usecase.AddTaskUseCaseProvider
import toothpick.config.Module

class AddTaskModule : Module() {
    init {
        bind(AddTaskUseCase::class.java).toProvider(AddTaskUseCaseProvider::class.java)
    }
}