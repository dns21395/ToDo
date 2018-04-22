package night.lines.todo.toothpick.usecase

import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

class RemoveTaskUseCaseProvider @Inject constructor(private val databaseRepository: DatabaseRepository) : Provider<RemoveTaskUseCase> {
    override fun get(): RemoveTaskUseCase = RemoveTaskUseCase(databaseRepository)
}