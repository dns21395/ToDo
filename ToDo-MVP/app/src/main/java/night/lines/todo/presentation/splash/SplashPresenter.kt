package night.lines.todo.presentation.splash

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Observable
import io.reactivex.functions.Function
import night.lines.todo.domain.interactor.main.AddTaskIdUseCase
import night.lines.todo.domain.interactor.main.GetTaskIdListUseCase
import night.lines.todo.domain.model.TaskID
import night.lines.todo.presentation.base.BasePresenter
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
@InjectViewState
class SplashPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                          private val getTaskIdListUseCase: GetTaskIdListUseCase,
                                          private val addTaskIdUseCase: AddTaskIdUseCase) : BasePresenter<SplashView>() {

    fun onViewPrepared() {
        compositeDisposable.add(
                getTaskIdListUseCase.execute()
                        .toObservable()
                        .flatMap {
                             when(it.size) {
                                0 ->  addTaskIdUseCase.execute(TaskID(0, "To-Do"))
                                else -> Observable.just(0L)
                            }
                        }
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            viewState.startMainActivity()
                        }
        )
    }
}