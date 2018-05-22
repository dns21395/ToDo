package night.lines.todo.toothpick.main.provider

import night.lines.todo.domain.interactor.main.GetTaskListIdUseCase
import night.lines.todo.domain.repository.PreferencesRepository
import javax.inject.Inject
import javax.inject.Provider

class GetTaskListIdUseCaseProvider @Inject constructor(private val preferencesRepository: PreferencesRepository) : Provider<GetTaskListIdUseCase> {
    override fun get(): GetTaskListIdUseCase = GetTaskListIdUseCase(preferencesRepository)
}