package night.lines.todo.presentation.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

}