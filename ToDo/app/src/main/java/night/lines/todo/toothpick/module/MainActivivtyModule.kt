package night.lines.todo.toothpick.module

import night.lines.todo.presentation.global.MainActivityController
import toothpick.config.Module

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainActivivtyModule : Module() {
    init {
        bind(MainActivityController::class.java).toInstance(MainActivityController())
    }
}