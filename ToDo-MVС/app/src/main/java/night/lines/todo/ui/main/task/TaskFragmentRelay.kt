package night.lines.todo.ui.main.task

import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject

class TaskFragmentRelay @Inject constructor()  {

    private val TAG = "TaskFragmentRelay"

    init {
        Log.d(TAG, "TaskFragmentRelay created")
    }

    private val taskFragmentStateRelay = PublishRelay.create<EnumTaskFragment>()

    val taskFragmentState: Observable<EnumTaskFragment> = taskFragmentStateRelay

    fun callTaskFragmentAction(action: EnumTaskFragment) {
        taskFragmentStateRelay.accept(action)
    }

    enum class EnumTaskFragment {
        FINISHED_ITEMS_VISIBILITY_UPDATED,
        ITEM_ADDED,
        SCROLL_TO_END
    }
}