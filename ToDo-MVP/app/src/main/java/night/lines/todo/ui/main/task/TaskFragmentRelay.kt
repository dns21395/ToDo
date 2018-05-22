package night.lines.todo.ui.main.task

import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

class TaskFragmentRelay {

    private val TAG = "TaskFragmentRelay"

    init {
        Log.d(TAG, "TASK FRAGMENT RELAY INITIALIZED")
    }

    private val taskFragmentStateRelay = PublishRelay.create<EnumTaskFragment>()

    val taskFragmentState: Observable<EnumTaskFragment> = taskFragmentStateRelay

    fun callTaskFragmentAction(action: EnumTaskFragment) {
        taskFragmentStateRelay.accept(action)
    }

    enum class EnumTaskFragment {
        UPDATE_ARRAY,
        ITEM_ADDED,
        SCROLL_TO_END,
    }
}