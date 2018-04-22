package night.lines.todo.toothpick.usecase

import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

class UpdateTaskUseCaseProvider @Inject constructor(private val databaseRepository: DatabaseRepository): Provider<UpdateTaskUseCase> {
    override fun get(): UpdateTaskUseCase = UpdateTaskUseCase(databaseRepository)
}