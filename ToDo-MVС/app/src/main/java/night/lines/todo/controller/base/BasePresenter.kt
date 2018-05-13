package night.lines.todo.controller.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by denisgabyshev on 18/03/2018.
 */
open class BasePresenter<V: MvpView> : MvpPresenter<V>() {
    val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}