package night.lines.todo.data.database.db.converter

import night.lines.todo.data.database.db.model.TaskModel
import night.lines.todo.domain.model.Task

class DatabaseConverterImpl : DatabaseConverter {
    override fun modelToDomain(model: TaskModel): Task = Task(model.id, model.taskName, model.date, model.isDone)

    override fun apiToModel(api: Task): TaskModel = TaskModel(api.id, api.taskName, api.date, api.isDone)
}