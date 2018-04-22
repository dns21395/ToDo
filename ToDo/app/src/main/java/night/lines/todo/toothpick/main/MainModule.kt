package night.lines.todo.toothpick.main

import night.lines.todo.ui.main.ToolbarImages
import night.lines.todo.presentation.global.MainActivityController
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