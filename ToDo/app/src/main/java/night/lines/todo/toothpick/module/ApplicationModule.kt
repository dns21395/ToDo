package night.lines.todo.toothpick.module

import android.content.Context
import night.lines.todo.data.database.db.AppDatabase
import night.lines.todo.data.database.db.converter.DatabaseConverter
import night.lines.todo.data.database.db.createAppDatabase
import night.lines.todo.domain.repository.DatabaseRepository
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.toothpick.provider.DatabaseConverterProvider
import night.lines.todo.toothpick.provider.DatabaseRepositoryProvider
import night.lines.todo.toothpick.provider.PreferencesRepositoryProvider
import night.lines.todo.util.SchedulerProviderImpl
import night.lines.todo.util.SchedulerProvider
import toothpick.config.Module

/**
 * Created by denisgabyshev on 19/03/2018.
 */
class ApplicationModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)
        bind(AppDatabase::class.java).toInstance(createAppDatabase(context))
        bind(SchedulerProvider::class.java).toInstance(SchedulerProviderImpl())
        bind(PreferencesRepository::class.java).toProvider(PreferencesRepositoryProvider::class.java)
        bind(DatabaseConverter::class.java).toProvider(DatabaseConverterProvider::class.java)
        bind(DatabaseRepository::class.java).toProvider(DatabaseRepositoryProvider::class.java)
    }
}