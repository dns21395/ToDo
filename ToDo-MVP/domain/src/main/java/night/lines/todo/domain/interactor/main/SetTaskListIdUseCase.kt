package night.lines.todo.domain.interactor.main

import io.reactivex.Flowable
import io.reactivex.Observable
import night.lines.todo.domain.interactor.type.UseCaseWithParameter
import night.lines.todo.domain.repository.PreferencesRepository

class SetTaskListIdUseCase constructor(private val preferencesRepository: PreferencesRepository)
    : UseCaseWithParameter<Long, Unit> {
    override fun execute(parameter: Long): Observable<Unit> = preferencesRepository.setTaskListId(parameter)
}