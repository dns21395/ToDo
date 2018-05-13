package night.lines.todo.domain.interactor.main

import io.reactivex.Observable
import night.lines.todo.domain.interactor.type.UseCaseWithParameter
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.DatabaseRepository

open class AddTaskUseCase constructor(private val databaseRepository: DatabaseRepository) : UseCaseWithParameter<Task, Long> {
    override fun execute(parameter: Task): Observable<Long> = databaseRepository.insertTask(parameter)
}

