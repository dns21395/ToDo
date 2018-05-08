package night.lines.todo.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by denisgabyshev on 20/03/2018.
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {
    companion object {
        private val PROGRESS_TAG = "bf_progress"
    }

    var activity: BaseActivity<*, *>? = null
    var rootView: View? = null
    private var viewDataBinding: T? = null
    private var viewModel: V? = null

    abstract val layoutRes: Int
    abstract fun getBindingVariable(): Int
    abstract fun getViewModel(): V
    abstract fun performDependencyInjection()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is BaseActivity<*, *>) {
            activity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        viewDataBinding?.let {
            rootView = it.root

        }
        return rootView
    }

    open fun onBackPressed() {}
}