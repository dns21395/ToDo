package night.lines.todo.toothpick.provider

import night.lines.todo.data.database.db.converter.DatabaseConverter
import night.lines.todo.data.database.db.converter.DatabaseConverterImpl
import javax.inject.Inject
import javax.inject.Provider

class DatabaseConverterProvider @Inject constructor() : Provider<DatabaseConverter> {
    override fun get(): DatabaseConverter = DatabaseConverterImpl()
}