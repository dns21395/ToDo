package night.lines.todo.data.database.db.converter

import night.lines.todo.data.database.db.model.TaskIDModel
import night.lines.todo.data.database.db.model.TaskModel
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.model.TaskID

class DatabaseConverterImpl : DatabaseConverter {
    override fun modelToDomain(model: TaskModel): Task = Task(model.id, model.listId, model.taskName, model.date, model.isDone)

    override fun apiToModel(api: Task): TaskModel = TaskModel(api.id, api.listId, api.taskName, api.date, api.isDone)

    override fun modelToDomain(model: TaskIDModel): TaskID = TaskID(model.id, model.name)

    override fun apiToModel(api: TaskID): TaskIDModel = TaskIDModel(api.id, api.name)
}