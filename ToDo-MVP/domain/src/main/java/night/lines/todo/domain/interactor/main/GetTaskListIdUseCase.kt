package night.lines.todo.domain.interactor.main

import io.reactivex.Observable
import night.lines.todo.domain.interactor.type.UseCase
import night.lines.todo.domain.repository.PreferencesRepository

class GetTaskListIdUseCase constructor(private val preferencesRepository: PreferencesRepository) : UseCase<Long?>  {
    override fun execute(): Observable<Long?> = preferencesRepository.getTaskListId()

}