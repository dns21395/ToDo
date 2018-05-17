package night.lines.todo.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
abstract class BaseFragment : Fragment() {

    private var parentActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
        }
    }

    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
    }

//    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector

    private fun inject() {
        val hasSupportFragmentInjector =  (parentActivity as HasSupportFragmentInjector)

        val fragmentInjector = hasSupportFragmentInjector.supportFragmentInjector()

        fragmentInjector.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(layoutRes, container, false)
}