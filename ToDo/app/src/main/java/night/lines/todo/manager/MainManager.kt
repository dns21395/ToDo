package night.lines.todo.manager

import io.reactivex.Observable
import night.lines.todo.domain.repository.PreferencesRepository
import javax.inject.Inject

/**
 * Created by denisgabyshev on 21/03/2018.
 */
class MainManager @Inject constructor(private val toolbarImages: ToolbarImages,
                                      private val preferencesRepository: PreferencesRepository) {

    fun getBackground(): Observable<Int> = Observable.fromCallable { toolbarImages.getBackground() }

    fun setFinishedTasksVisibility(status: Boolean) = preferencesRepository.setFinishedTasksVisibility(status)

    fun getFinishedTasksVisibility() = preferencesRepository.getFinishedTasksVisibility()
}