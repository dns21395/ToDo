package night.lines.todo.toothpick.main

import night.lines.todo.manager.ToolbarImages
import night.lines.todo.manager.MainActivityController
import toothpick.config.Module

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainModule : Module() {
    init {
        bind(MainActivityController::class.java).toInstance(MainActivityController())
        bind(ToolbarImages::class.java).toInstance(ToolbarImages())
    }
}