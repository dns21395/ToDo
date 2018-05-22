package night.lines.todo.domain.interactor.main

import io.reactivex.Observable
import night.lines.todo.domain.interactor.type.UseCaseWithParameter
import night.lines.todo.domain.model.TaskID
import night.lines.todo.domain.repository.DatabaseRepository

class AddTaskIdUseCase constructor(private val databaseRepository: DatabaseRepository) : UseCaseWithParameter<TaskID, Long> {
    override fun execute(parameter: TaskID): Observable<Long> = databaseRepository.insertTaskId(parameter)

}