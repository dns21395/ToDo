package night.lines.todo.domain.interactor.main

import io.reactivex.Flowable
import night.lines.todo.domain.interactor.type.FlowableUseCaseWithParameter
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.DatabaseRepository

class GetTasksUseCase(private val databaseRepository: DatabaseRepository) : FlowableUseCaseWithParameter<Boolean, ArrayList<Task>> {
    override fun execute(parameter: Boolean): Flowable<ArrayList<Task>> = databaseRepository.getTasks(parameter)


}