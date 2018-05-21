package night.lines.todo.toothpick.provider

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import javax.inject.Inject
import javax.inject.Provider

class LinearLayoutManagerProvider @Inject constructor(private val context: Context) : Provider<LinearLayoutManager> {
    override fun get(): LinearLayoutManager = LinearLayoutManager(context)
}