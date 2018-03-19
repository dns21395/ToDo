package night.lines.todo.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Entity
data class Section (
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        var date: Long,
        var name: String
)