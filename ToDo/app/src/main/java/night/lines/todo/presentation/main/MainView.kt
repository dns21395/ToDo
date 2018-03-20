package night.lines.todo.presentation.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
 
    fun showAddTaskFragment()

    @StateStrategyType(SkipStrategy::class)
    fun hideAddTaskFragment()
}