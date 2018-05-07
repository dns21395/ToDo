package night.lines.todo.presentation.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

/**
 * Created by denisgabyshev on 18/03/2018.
 */
interface MainNavigator  {

    fun showAddTaskFragment()

    fun hideAddTaskFragment()

    fun setToolbarBackground(background: Int)

    fun updateIconCheckFinishedItemsVisibility(drawable: Int)
}