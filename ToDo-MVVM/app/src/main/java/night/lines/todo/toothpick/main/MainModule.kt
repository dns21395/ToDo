package night.lines.todo.toothpick.main

import night.lines.todo.presentation.main.MainActivityViewModel
import night.lines.todo.presentation.main.task.TaskFragmentViewModel
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import toothpick.config.Module

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainModule : Module() {
    init {
        bind(AddTaskFragmentRelay::class.java).singletonInScope()
        bind(TaskFragmentRelay::class.java).singletonInScope()
    }
}