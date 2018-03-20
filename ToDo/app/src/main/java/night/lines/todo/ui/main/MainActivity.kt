package night.lines.todo.ui.main

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import night.lines.todo.R
import night.lines.todo.presentation.main.MainPresenter
import night.lines.todo.presentation.main.MainView
import night.lines.todo.toothpick.DI
import night.lines.todo.ui.main.task.TaskFragment
import toothpick.Toothpick

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TaskFragment())
                .commitAllowingStateLoss()

    }

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return Toothpick
                .openScopes(DI.APP_SCOPE, DI.MAIN_SCOPE)
                .getInstance(MainPresenter::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) Toothpick.closeScope(DI.MAIN_SCOPE)
    }
}