package night.lines.todo.toothpick.main

import night.lines.todo.domain.interactor.main.GetTaskIdListUseCase
import night.lines.todo.domain.interactor.main.GetTaskListIdUseCase
import night.lines.todo.domain.interactor.main.SetTaskListIdUseCase
import night.lines.todo.toothpick.main.provider.GetTaskIdListUseCaseProvider
import night.lines.todo.toothpick.main.provider.GetTaskListIdUseCaseProvider
import night.lines.todo.toothpick.main.provider.MainNavigatonAdapterProvider
import night.lines.todo.toothpick.main.provider.SetTaskListIdUseCaseProvider
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
        bind(GetTaskIdListUseCase::class.java).toProvider(GetTaskIdListUseCaseProvider::class.java).singletonInScope()
        bind(GetTaskListIdUseCase::class.java).toProvider(GetTaskListIdUseCaseProvider::class.java).singletonInScope()
        bind(SetTaskListIdUseCase::class.java).toProvider(SetTaskListIdUseCaseProvider::class.java).singletonInScope()
        bind(MainNavigationAdapter::class.java).toProvider(MainNavigatonAdapterProvider::class.java).singletonInScope()
    }
}