package night.lines.todo.data.database.db.converter

import night.lines.todo.data.database.db.model.TaskIDModel
import night.lines.todo.data.database.db.model.TaskModel
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.model.TaskID

interface DatabaseConverter {
    fun modelToDomain(model: TaskModel): Task

    fun modelToDomain(model: TaskIDModel): TaskID

    fun apiToModel(api: Task): TaskModel

    fun apiToModel(api: TaskID): TaskIDModel
}