package night.lines.todo.domain.interactor.main

import io.reactivex.Flowable
import night.lines.todo.domain.interactor.type.FlowableUseCaseWithParameter
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.repository.DatabaseRepository

class GetTasksUseCase(private val databaseRepository: DatabaseRepository) : FlowableUseCaseWithParameter<GetTasksUseCaseData, ArrayList<Task>> {
    override fun execute(parameter: GetTasksUseCaseData): Flowable<ArrayList<Task>> = databaseRepository.getTasks(parameter.isFinished, parameter.listId)
}

data class GetTasksUseCaseData(val isFinished: Boolean, val listId: Long)