package night.lines.todo.toothpick.main


import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import toothpick.config.Module

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainModule : Module() {
    init {
        bind(AddTaskFragmentRelay::class.java).toInstance(AddTaskFragmentRelay())
        bind(TaskFragmentRelay::class.java).toInstance(TaskFragmentRelay())
    }
}