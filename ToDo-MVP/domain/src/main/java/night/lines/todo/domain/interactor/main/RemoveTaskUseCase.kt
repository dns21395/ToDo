package night.lines.todo.domain.interactor.main

import io.reactivex.Observable
import night.lines.todo.domain.interactor.type.UseCaseWithParameter
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.DatabaseRepository

class RemoveTaskUseCase(private val databaseRepository: DatabaseRepository) : UseCaseWithParameter<Task, Unit> {
    override fun execute(parameter: Task): Observable<Unit> = databaseRepository.removeTask(parameter)
}