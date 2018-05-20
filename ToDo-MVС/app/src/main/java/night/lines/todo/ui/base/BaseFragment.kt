package night.lines.todo.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by denisgabyshev on 20/03/2018.
 */
abstract class BaseFragment : DaggerFragment() {

    private var parentActivity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
        }
    }



    abstract val layoutRes: Int






    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(layoutRes, container, false)
}