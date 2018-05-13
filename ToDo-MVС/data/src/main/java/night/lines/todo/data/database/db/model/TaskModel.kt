package night.lines.todo.data.database.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Entity(tableName = "task")
data class TaskModel (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val taskName: String,
        var date: Long,
        var isDone: Boolean = false

)