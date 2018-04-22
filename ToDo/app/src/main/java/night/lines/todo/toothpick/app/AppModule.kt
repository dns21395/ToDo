package night.lines.todo.toothpick.app

import android.content.Context
import night.lines.todo.data.database.db.AppDatabase
import night.lines.todo.data.database.db.converter.DatabaseConverter
import night.lines.todo.data.database.db.converter.DatabaseConverterImpl
import night.lines.todo.data.database.db.createAppDatabase
import night.lines.todo.data.prefs.PreferencesRepositoryImpl
import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.domain.repository.DatabaseRepository
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.model.system.scheduler.AppSchedulerProvider
import night.lines.todo.model.system.scheduler.SchedulerProvider
import night.lines.todo.toothpick.usecase.AddTaskUseCaseProvider
import night.lines.todo.toothpick.usecase.GetTasksUseCaseProvider
import night.lines.todo.toothpick.usecase.RemoveTaskUseCaseProvider
import night.lines.todo.toothpick.usecase.UpdateTaskUseCaseProvider
import toothpick.config.Module

/**
 * Created by denisgabyshev on 19/03/2018.
 */
class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)
        bind(AppDatabase::class.java).toInstance(createAppDatabase(context))
        bind(SchedulerProvider::class.java).toInstance(AppSchedulerProvider())
        bind(PreferencesRepository::class.java).toInstance(PreferencesRepositoryImpl(context))
        bind(DatabaseConverter::class.java).toProvider(DatabaseConverterProvider::class.java)
        bind(DatabaseRepository::class.java).toProvider(DatabaseRepositoryProvider::class.java)
    }
}