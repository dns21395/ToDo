package night.lines.todo.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Entity(foreignKeys = ([(ForeignKey(
        entity = Section::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("sectionId"),
        onDelete = ForeignKey.CASCADE
        ))]))
data class Task (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        var sectionId: Long?,
        val taskName: String,
        var date: Long,
        var isDone: Boolean = false

)