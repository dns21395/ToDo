package night.lines.todo.ui.main

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import android.widget.FrameLayout
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import night.lines.todo.R
import night.lines.todo.presentation.global.MainActivityController
import night.lines.todo.presentation.main.MainPresenter
import night.lines.todo.presentation.main.MainView
import night.lines.todo.toothpick.DI
import night.lines.todo.toothpick.module.MainActivityModule
import night.lines.todo.ui.main.addtask.AddTaskFragment
import night.lines.todo.ui.main.task.TaskFragment
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private var bottomFrameLayoutId = 0

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return Toothpick
                .openScopes(DI.MAIN_SCOPE)
                .getInstance(MainPresenter::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TaskFragment())
                .commitAllowingStateLoss()

        fab.setOnClickListener {
            presenter.onFabButtonClicked()
        }

        presenter.onViewPrepared()

    }

    override fun onDestroy() {
        super.onDestroy()

        if(isFinishing) Toothpick.closeScope(DI.MAIN_SCOPE)
    }

    override fun showAddTaskFragment() {
        fab.hide()

        val bottomFrameLayout = FrameLayout(applicationContext)

        val params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        bottomFrameLayout.layoutParams = params

        bottomFrameLayoutId = View.generateViewId()

        bottomFrameLayout.id = bottomFrameLayoutId

        parentConstraint.addView(bottomFrameLayout)

        val constraintSet = ConstraintSet()

        constraintSet.clone(parentConstraint)

        constraintSet.connect(bottomFrameLayoutId, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM )

        constraintSet.connect(bottomFrameLayoutId, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)

        constraintSet.connect(bottomFrameLayoutId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        constraintSet.clear(frameLayout.id, ConstraintSet.BOTTOM)

        constraintSet.connect(frameLayout.id, ConstraintSet.BOTTOM, bottomFrameLayoutId, ConstraintSet.TOP)

        constraintSet.applyTo(parentConstraint)

        supportFragmentManager.beginTransaction().replace(bottomFrameLayoutId, AddTaskFragment()).commitAllowingStateLoss()
    }

    override fun hideAddTaskFragment() {
        parentConstraint.removeView(findViewById(bottomFrameLayoutId))

        val constraintSet = ConstraintSet()
        constraintSet.clone(parentConstraint)

        constraintSet.clear(frameLayout.id, ConstraintSet.BOTTOM)

        constraintSet.connect(frameLayout.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

        constraintSet.applyTo(parentConstraint)

        bottomFrameLayoutId = 0

        fab.show()
    }

}