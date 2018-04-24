package night.lines.todo.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by denisgabyshev on 20/03/2018.
 */
abstract class BaseFragment : MvpAppCompatFragment() {
    companion object {
        private val PROGRESS_TAG = "bf_progress"
    }

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let { restoreState(it) }
    }

    open protected fun restoreState(state: Bundle) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(layoutRes, container, false)

    open fun onBackPressed() {}
}