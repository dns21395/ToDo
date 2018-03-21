package night.lines.todo.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Entity
data class Task (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val taskName: String,
        var date: Long,
        var isDone: Boolean = false

)