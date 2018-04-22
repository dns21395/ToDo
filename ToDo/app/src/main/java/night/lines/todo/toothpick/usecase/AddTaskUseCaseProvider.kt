package night.lines.todo.toothpick.usecase

import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

class AddTaskUseCaseProvider @Inject constructor(private val databaseRepository: DatabaseRepository): Provider<AddTaskUseCase> {
    override fun get(): AddTaskUseCase = AddTaskUseCase(databaseRepository)
}