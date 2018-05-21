package night.lines.todo.data.database.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Entity(tableName = "task",
        foreignKeys = [(ForeignKey(
                entity = TaskIDModel::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("listId"),
                onDelete = ForeignKey.CASCADE))])
data class TaskModel (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val listId: Long?,
        var taskName: String,
        val date: Long,
        var isDone: Boolean = false

)