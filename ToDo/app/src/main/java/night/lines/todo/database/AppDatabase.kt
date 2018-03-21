package night.lines.todo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import night.lines.todo.database.dao.TaskDao
import night.lines.todo.database.model.Task

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

}