package night.lines.todo.presentation.global

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainActivityController {
    var sectionId: Long? = null

    private val addTaskFragmentStateRelay = PublishRelay.create<EnumAddTaskFragment>()

    val addTaskFragmentState: Observable<EnumAddTaskFragment> = addTaskFragmentStateRelay

    fun callAddTaskFragmentAction(action: EnumAddTaskFragment) {
        addTaskFragmentStateRelay.accept(action)
    }

    enum class EnumAddTaskFragment {
        SHOW,
        HIDE
    }

}