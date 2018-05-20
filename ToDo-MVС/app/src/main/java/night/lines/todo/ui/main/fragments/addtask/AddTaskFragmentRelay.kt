package night.lines.todo.ui.main.fragments.addtask

import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class AddTaskFragmentRelay @Inject constructor() {

    private val TAG = "AddTaskFragmentRelay"

    init {
        Log.d(TAG, "AddTaskFragmentRelay created")
    }

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