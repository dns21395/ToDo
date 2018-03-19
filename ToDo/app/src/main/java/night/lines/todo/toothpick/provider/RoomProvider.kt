package night.lines.todo.toothpick.provider

import android.arch.persistence.room.Room
import android.content.Context
import night.lines.todo.database.AppDatabase
import night.lines.todo.database.manager.AppDatabaseManager
import night.lines.todo.database.manager.DatabaseManager
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by denisgabyshev on 19/03/2018.
 */
class RoomProvider @Inject constructor(private val context: Context,
                                       private val appDatabase: AppDatabase) : Provider<DatabaseManager> {
    override fun get(): DatabaseManager =
            AppDatabaseManager(context, appDatabase.commonDao(), appDatabase.sectionDao(), appDatabase.taskDao())


}