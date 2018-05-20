package night.lines.todo.ui.main.fragments.task

import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject


class TaskFragmentRelay @Inject constructor() {

    private val TAG = "TaskFragmentRelay"

    init {
<<<<<<< HEAD:ToDo-MVС/app/src/main/java/night/lines/todo/ui/main/fragments/task/TaskFragmentRelay.kt
        Log.d(TAG, "INIT")
    }

=======
        Log.d(TAG, "TaskFragmentRelay created")
    }
>>>>>>> soso:ToDo-MVС/app/src/main/java/night/lines/todo/ui/main/task/TaskFragmentRelay.kt

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