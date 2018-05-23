package night.lines.todo.toothpick.main.provider

import night.lines.todo.domain.interactor.main.GetTaskIdByIdUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

class GetTaskIdByIdUseCaseProvider @Inject constructor(private val databaseRepository: DatabaseRepository) : Provider<GetTaskIdByIdUseCase> {
    override fun get(): GetTaskIdByIdUseCase = GetTaskIdByIdUseCase(databaseRepository)
}