package night.lines.todo.toothpick.task.provider

import night.lines.todo.ui.main.task.TaskAdapter
import javax.inject.Inject
import javax.inject.Provider
import android.content.Context


class TaskAdapterProvider @Inject constructor(private val context: Context) : Provider<TaskAdapter> {
    override fun get(): TaskAdapter = TaskAdapter(context)
}