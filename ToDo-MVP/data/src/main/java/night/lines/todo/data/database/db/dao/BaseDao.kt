package night.lines.todo.data.database.db.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update

/**
 * Created by denisgabyshev on 18/03/2018.
 */
interface BaseDao<in T> {
    @Insert
    fun insert(obj: T): Long

    @Insert
    fun insert(vararg obj: T)

    @Update
    fun update(obj: T)

    @Delete
    fun delete(obj: T)
}