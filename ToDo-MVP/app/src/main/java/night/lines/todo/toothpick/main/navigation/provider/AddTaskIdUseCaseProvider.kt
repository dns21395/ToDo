package night.lines.todo.toothpick.main.navigation.provider

import night.lines.todo.domain.interactor.main.AddTaskIdUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

class AddTaskIdUseCaseProvider @Inject constructor(private val databaseRepository: DatabaseRepository): Provider<AddTaskIdUseCase> {
    override fun get(): AddTaskIdUseCase = AddTaskIdUseCase(databaseRepository)
}