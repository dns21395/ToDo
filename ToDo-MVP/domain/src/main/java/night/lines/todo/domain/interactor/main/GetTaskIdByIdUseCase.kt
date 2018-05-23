package night.lines.todo.domain.interactor.main

import io.reactivex.Observable
import night.lines.todo.domain.interactor.type.UseCaseWithParameter
import night.lines.todo.domain.model.TaskID
import night.lines.todo.domain.repository.DatabaseRepository

class GetTaskIdByIdUseCase constructor(private val databaseRepository: DatabaseRepository): UseCaseWithParameter<Long, TaskID> {
    override fun execute(parameter: Long): Observable<TaskID> = databaseRepository.getTaskIdById(parameter)
}