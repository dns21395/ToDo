package night.lines.todo.model.interactor.task

import night.lines.todo.model.data.storage.Prefs
import javax.inject.Inject

/**
 * Created by denisgabyshev on 22/03/2018.
 */
class TaskInteractor @Inject constructor(private val prefs: Prefs) {

    fun getFinishedTasksVisibility(): Int
        = when(prefs.getFinishedTasksVisibility()) {
            true -> 1
            false -> 0
        }

}