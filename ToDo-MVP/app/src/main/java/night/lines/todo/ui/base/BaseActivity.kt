package night.lines.todo.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.MvpDelegate

abstract class BaseActivity : AppCompatActivity() {

    /**
     * @return The [MvpDelegate] used by this Activity.
     */
    val mvpDelegate: MvpDelegate<*> by lazy {
        MvpDelegate(this)
    }

    protected abstract fun openScope()

    protected open fun closeScope() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openScope()
        mvpDelegate.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        mvpDelegate.onAttach()
    }

    override fun onResume() {
        super.onResume()

        mvpDelegate.onAttach()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        mvpDelegate.onSaveInstanceState(outState!!)
        mvpDelegate.onDetach()
    }

    override fun onStop() {
        super.onStop()

        mvpDelegate.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()

        mvpDelegate.onDestroyView()

        if (isFinishing) {
            closeScope()
            mvpDelegate.onDestroy()
        }
    }
}