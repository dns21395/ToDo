package night.lines.todo.toothpick.main.provider

import night.lines.todo.domain.interactor.main.SetTaskListIdUseCase
import night.lines.todo.domain.repository.PreferencesRepository
import javax.inject.Inject
import javax.inject.Provider

class SetTaskListIdUseCaseProvider @Inject constructor(private val preferencesRepository: PreferencesRepository) : Provider<SetTaskListIdUseCase> {
    override fun get(): SetTaskListIdUseCase = SetTaskListIdUseCase(preferencesRepository)
}