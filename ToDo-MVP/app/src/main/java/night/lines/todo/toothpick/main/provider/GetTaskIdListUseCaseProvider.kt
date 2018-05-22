package night.lines.todo.toothpick.main.provider

import night.lines.todo.domain.interactor.main.GetTaskIdListUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

class GetTaskIdListUseCaseProvider @Inject constructor(private val databaseRepository: DatabaseRepository) : Provider<GetTaskIdListUseCase> {
    override fun get(): GetTaskIdListUseCase = GetTaskIdListUseCase(databaseRepository)
}