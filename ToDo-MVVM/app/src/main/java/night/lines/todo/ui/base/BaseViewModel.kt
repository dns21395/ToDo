package night.lines.todo.ui.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import night.lines.todo.util.SchedulerProvider

abstract class BaseViewModel<V> constructor(val schedulerProvider: SchedulerProvider) : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    var navigator: V? = null

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    abstract fun onViewPrepared()


}