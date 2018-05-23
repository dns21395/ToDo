package night.lines.todo.toothpick.main

import night.lines.todo.domain.interactor.main.GetTaskIdByIdUseCase
import night.lines.todo.domain.interactor.main.GetTaskIdListUseCase
import night.lines.todo.domain.interactor.main.GetTaskListIdUseCase
import night.lines.todo.domain.interactor.main.SetTaskListIdUseCase
import night.lines.todo.toothpick.main.provider.*
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.navigation.MainNavigationAdapter
import toothpick.config.Module

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainModule : Module() {
    init {
        bind(AddTaskFragmentRelay::class.java).toInstance(AddTaskFragmentRelay())
        bind(TaskFragmentRelay::class.java).toInstance(TaskFragmentRelay())
        bind(GetTaskListIdUseCase::class.java).toProvider(GetTaskListIdUseCaseProvider::class.java).providesSingletonInScope()
        bind(SetTaskListIdUseCase::class.java).toProvider(SetTaskListIdUseCaseProvider::class.java).providesSingletonInScope()
        bind(MainNavigationAdapter::class.java).toProvider(MainNavigatonAdapterProvider::class.java).providesSingletonInScope()
        bind(GetTaskIdByIdUseCase::class.java).toProvider(GetTaskIdByIdUseCaseProvider::class.java).providesSingletonInScope()
    }
}