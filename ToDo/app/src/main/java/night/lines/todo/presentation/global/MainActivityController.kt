package night.lines.todo.presentation.global

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class MainActivityController {
    private val addTaskFragmentStateRelay = PublishRelay.create<EnumAddTaskFragment>()

    val addTaskFragmentState: Observable<EnumAddTaskFragment> = addTaskFragmentStateRelay

    fun callAddTaskFragmentAction(action: EnumAddTaskFragment) {
        addTaskFragmentStateRelay.accept(action)
    }

    enum class EnumAddTaskFragment {
        SHOW,
        HIDE
    }

    private val taskFragmentStateRelay = PublishRelay.create<EnumTaskFragment>()

    val taskFragmentState: Observable<EnumTaskFragment> = taskFragmentStateRelay

    fun callTaskFragmentAction(action: EnumTaskFragment) {
        taskFragmentStateRelay.accept(action)
    }

    enum class EnumTaskFragment {
        FINISHED_ITEMS_VISIBILITY_UPDATED,
        ITEM_ADDED
    }

}