package night.lines.todo.toothpick.provider.usecase

import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

class GetTasksUseCaseProvider @Inject constructor(private val databaseRepository: DatabaseRepository): Provider<GetTasksUseCase> {
    override fun get(): GetTasksUseCase = GetTasksUseCase(databaseRepository)
}