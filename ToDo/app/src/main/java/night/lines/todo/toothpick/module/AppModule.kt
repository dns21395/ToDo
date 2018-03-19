package night.lines.todo.toothpick.module

import android.arch.persistence.room.Room
import android.content.Context
import night.lines.todo.database.AppDatabase
import night.lines.todo.database.manager.AppDatabaseManager
import night.lines.todo.database.manager.DatabaseManager
import night.lines.todo.toothpick.provider.RoomProvider
import toothpick.config.Module

/**
 * Created by denisgabyshev on 19/03/2018.
 */
class AppModule(context: Context) : Module() {
    init {
        bind(Context::class.java).toInstance(context)
        bind(AppDatabase::class.java).toInstance(Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "todo.db").build()
        )
        bind(DatabaseManager::class.java).toProvider(RoomProvider::class.java).providesSingletonInScope()
    }
}