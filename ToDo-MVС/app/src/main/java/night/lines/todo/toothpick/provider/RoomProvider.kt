package night.lines.todo.toothpick.provider

import android.arch.persistence.room.Room
import android.content.Context
import night.lines.todo.data.database.db.AppDatabase
import javax.inject.Inject
import javax.inject.Provider

class RoomProvider @Inject constructor(private val context: Context) : Provider<AppDatabase> {
    override fun get(): AppDatabase =
            Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "todo.db").build()
}