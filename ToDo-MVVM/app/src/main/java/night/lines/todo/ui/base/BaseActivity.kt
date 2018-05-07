package night.lines.todo.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {
    abstract var layoutRes: Int

    private var viewModel: V? = null
    private var viewDataBinding: T? = null
    abstract var bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    abstract fun performDependencyInjection()

    abstract fun getViewModel(): V

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutRes)
        this.viewModel = if(viewModel == null) getViewModel() else viewModel
        viewDataBinding?.let {
            it.setVariable(bindingVariable, viewModel)
            it.executePendingBindings()
        }
    }
}