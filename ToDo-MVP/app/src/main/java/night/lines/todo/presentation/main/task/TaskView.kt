package night.lines.todo.presentation.main.task

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import night.lines.todo.domain.model.Task

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface TaskView : MvpView {
    fun updateTaskArray(array: ArrayList<Task>)

    fun scrollToEnd()
}