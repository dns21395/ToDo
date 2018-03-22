package night.lines.todo.toothpick.provider

import night.lines.todo.model.data.database.AppDatabase
import night.lines.todo.model.data.database.manager.AppDatabaseManager
import night.lines.todo.model.data.database.manager.DatabaseManager
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by denisgabyshev on 19/03/2018.
 */
class RoomProvider @Inject constructor(private val appDatabase: AppDatabase) : Provider<DatabaseManager> {
    override fun get(): DatabaseManager =
            AppDatabaseManager(appDatabase.taskDao())


}