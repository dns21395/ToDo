package night.lines.todo.domain.interactor.main

import io.reactivex.Flowable
import night.lines.todo.domain.interactor.type.FlowableUseCase
import night.lines.todo.domain.model.TaskID
import night.lines.todo.domain.repository.DatabaseRepository

class GetTaskIdListUseCase(private val databaseRepository: DatabaseRepository) : FlowableUseCase<ArrayList<TaskID>> {
    override fun execute(): Flowable<ArrayList<TaskID>> = databaseRepository.getTaskIdList()
}