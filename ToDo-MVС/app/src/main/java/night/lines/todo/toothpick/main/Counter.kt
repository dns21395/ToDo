package night.lines.todo.toothpick.main

import android.util.Log
import javax.inject.Inject

class Counter @Inject constructor() {

    private val TAG = "Counter"

    init {
        Log.d(TAG, "Counter created")
    }
}