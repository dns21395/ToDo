package night.lines.todo.presentation.main.addtask

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by denisgabyshev on 20/03/2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface AddTaskView : MvpView {

}