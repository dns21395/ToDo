package night.lines.todo.ui.main

import dagger.Module
import dagger.Provides
import night.lines.todo.dagger.MainScope
import night.lines.todo.dagger.application.ForApplication
import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import night.lines.todo.ui.main.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.task.TaskFragmentRelay
import javax.inject.Singleton

@Module
class MainActivityModule {
    @Provides
    fun provideAddTaskUseCase(databaseRepository: DatabaseRepository): AddTaskUseCase
            = AddTaskUseCase(databaseRepository)

    @Provides
    fun provideGetTasksUseCase(databaseRepository: DatabaseRepository): GetTasksUseCase
            = GetTasksUseCase(databaseRepository)

    @Provides
    fun provideRemoveTaskUseCase(databaseRepository: DatabaseRepository): RemoveTaskUseCase
            = RemoveTaskUseCase(databaseRepository)

    @Provides
    fun provideUpdateTaskUseCase(databaseRepository: DatabaseRepository): UpdateTaskUseCase
            = UpdateTaskUseCase(databaseRepository)

    @Provides
    @MainScope
    fun provideTaskFragmentRelay(): TaskFragmentRelay = TaskFragmentRelay()

    @Provides
    @MainScope
    fun provideAddTaskFragmentRelay(): AddTaskFragmentRelay = AddTaskFragmentRelay()
}