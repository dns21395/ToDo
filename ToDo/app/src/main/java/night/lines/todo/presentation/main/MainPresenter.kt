package night.lines.todo.presentation.main

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import night.lines.todo.model.AppTestManager
import night.lines.todo.presentation.global.BasePresenter
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class MainPresenter @Inject constructor(private val testManager: AppTestManager) : BasePresenter<MainView>() {

    private val TAG = "MainPresenter"

    fun onClickButton(text: String) {
        Log.d(TAG, "$testManager")
    }
}