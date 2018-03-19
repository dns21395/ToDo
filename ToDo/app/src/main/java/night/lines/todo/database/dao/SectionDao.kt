package night.lines.todo.database.dao

import android.arch.persistence.room.Dao
import night.lines.todo.database.model.Section

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@Dao
interface SectionDao : BaseDao<Section> {
}