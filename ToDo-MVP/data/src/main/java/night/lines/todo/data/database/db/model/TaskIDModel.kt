package night.lines.todo.data.database.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "task_id")
data class TaskIDModel (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        var name: String
)