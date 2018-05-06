package night.lines.todo.data.database.db.converter

import night.lines.todo.data.database.db.model.TaskModel
import night.lines.todo.domain.model.Task

interface DatabaseConverter {
    fun modelToDomain(model: TaskModel): Task

    fun apiToModel(api: Task): TaskModel
}