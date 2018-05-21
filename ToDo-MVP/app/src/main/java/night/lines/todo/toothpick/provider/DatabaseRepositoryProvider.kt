package night.lines.todo.toothpick.provider

import night.lines.todo.data.database.db.AppDatabase
import night.lines.todo.data.database.db.converter.DatabaseConverter
import night.lines.todo.data.database.db.manager.DatabaseRepositoryImpl
import night.lines.todo.domain.repository.DatabaseRepository
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by denisgabyshev on 19/03/2018.
 */
class DatabaseRepositoryProvider @Inject constructor(private val databaseConverter: DatabaseConverter, private val appDatabase: AppDatabase) : Provider<DatabaseRepository> {
    override fun get(): DatabaseRepository = DatabaseRepositoryImpl(databaseConverter, appDatabase.taskDao(), appDatabase.taskIDDao())
}