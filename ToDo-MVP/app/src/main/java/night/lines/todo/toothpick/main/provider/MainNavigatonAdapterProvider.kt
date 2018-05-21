package night.lines.todo.toothpick.main.provider

import android.content.Context
import night.lines.todo.ui.main.navigation.MainNavigationAdapter
import javax.inject.Inject
import javax.inject.Provider

class MainNavigatonAdapterProvider @Inject constructor(private val context: Context) : Provider<MainNavigationAdapter> {
    override fun get(): MainNavigationAdapter = MainNavigationAdapter(context)
}