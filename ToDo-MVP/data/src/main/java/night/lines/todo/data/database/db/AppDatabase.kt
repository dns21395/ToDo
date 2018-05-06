package night.lines.todo.data.database.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import night.lines.todo.data.database.db.dao.TaskDao
import night.lines.todo.data.database.db.model.TaskModel

/**
 * Created by denisgabyshev on 18/03/2018.
 */

fun createAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo.db"
        ).build()


@Database(entities = [TaskModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

}