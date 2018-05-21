package night.lines.todo.toothpick.task

import night.lines.todo.presentation.main.task.TaskPresenter
import night.lines.todo.toothpick.task.provider.TaskAdapterProvider
import night.lines.todo.toothpick.task.provider.TaskPresenterProvider
import night.lines.todo.ui.main.task.TaskAdapter
import toothpick.config.Module

class TaskModule : Module() {
    init {
        bind(TaskPresenter::class.java).toProvider(TaskPresenterProvider::class.java).singletonInScope()
        bind(TaskAdapter::class.java).toProvider(TaskAdapterProvider::class.java).singletonInScope()
    }
}