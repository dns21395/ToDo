package night.lines.todo.toothpick.main

import night.lines.todo.ui.main.AddTaskActionsRelay
import night.lines.todo.ui.main.MainNavigationRelay
import toothpick.config.Module

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainModule : Module() {
    init {
        bind(MainNavigationRelay::class.java).toInstance(MainNavigationRelay())
        bind(AddTaskActionsRelay::class.java).toInstance(AddTaskActionsRelay())
    }
}