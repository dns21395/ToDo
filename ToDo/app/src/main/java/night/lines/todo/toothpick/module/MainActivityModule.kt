package night.lines.todo.toothpick.module

import night.lines.todo.model.interactor.main.MainInteractor
import night.lines.todo.model.interactor.task.TaskInteractor
import night.lines.todo.model.repository.toolbarimages.AppToolbarImages
import night.lines.todo.model.repository.toolbarimages.ToolbarImages
import night.lines.todo.presentation.global.MainActivityController
import toothpick.config.Module

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainActivityModule : Module() {
    init {
        bind(MainActivityController::class.java).toInstance(MainActivityController())
        bind(ToolbarImages::class.java).toInstance(AppToolbarImages())
        bind(MainInteractor::class.java).to(MainInteractor::class.java)
        bind(TaskInteractor::class.java).to(TaskInteractor::class.java)
    }
}