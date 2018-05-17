package night.lines.todo.dagger.application.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import night.lines.todo.data.database.db.AppDatabase
import night.lines.todo.data.database.db.converter.DatabaseConverter
import night.lines.todo.data.database.db.converter.DatabaseConverterImpl
import night.lines.todo.data.database.db.manager.DatabaseRepositoryImpl
import night.lines.todo.data.prefs.PreferencesRepositoryImpl
import night.lines.todo.domain.repository.DatabaseRepository
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.dagger.application.ForApplication
import night.lines.todo.util.SchedulerProvider
import night.lines.todo.util.SchedulerProviderImpl
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    internal fun provideContext(application: Application): Context = application

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase
        = Room.databaseBuilder(application, AppDatabase::class.java, "todo.db").build()

    @Provides
    internal fun provideDatabaseConverter(databaseConverterImpl: DatabaseConverterImpl): DatabaseConverter
        = databaseConverterImpl

    @Provides
    @Singleton
    internal fun provideDatabaseRepository(databaseConverter: DatabaseConverter, appDatabase: AppDatabase): DatabaseRepository
        =  DatabaseRepositoryImpl(databaseConverter, appDatabase.taskDao())

    @Provides
    @Singleton
    internal fun providePreferencesRepository(context: Context): PreferencesRepository
        = PreferencesRepositoryImpl(context)
}