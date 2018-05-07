package night.lines.todo.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import night.lines.todo.BR
import night.lines.todo.R
import night.lines.todo.databinding.ActivityMainBinding
import night.lines.todo.presentation.main.MainActivityViewModel
import night.lines.todo.presentation.main.MainNavigator
import night.lines.todo.toothpick.DI
import night.lines.todo.toothpick.main.MainModule
import night.lines.todo.ui.base.BaseActivity
import night.lines.todo.ui.main.task.TaskFragment
import night.lines.todo.util.SchedulerProvider
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(), MainNavigator {
    override var layoutRes = R.layout.activity_main
    override var bindingVariable: Int = BR.viewModel

    @Inject lateinit var mainActivityViewModel: MainActivityViewModel

    override fun performDependencyInjection() {
        Toothpick.openScopes(DI.APP_SCOPE, DI.MAIN_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@MainActivity, this)
            installModules(MainModule())
        }
    }

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel.navigator = this
        mainActivityViewModel.onViewPrepared()


//        setMenu()

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, TaskFragment())
                .commitAllowingStateLoss()

//        if(presenter.bottomFrameLayoutId != 0) createFrameLayout()
//
//        fab.setOnClickListener {
//            presenter.onFabButtonClicked()
//        }
//
//        presenter.onViewPrepared()
    }

    override fun showAddTaskFragment() {}

    override fun hideAddTaskFragment() {}

    override fun updateIconCheckFinishedItemsVisibility(drawable: Int) {}

    override fun getViewModel(): MainActivityViewModel = mainActivityViewModel


//
//    private fun createFrameLayout() {
//        fab.hide()
//
//        val bottomFrameLayout = FrameLayout(applicationContext)
//
//        val params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
//
//        bottomFrameLayout.layoutParams = params
//
//        if(presenter.bottomFrameLayoutId == 0) presenter.bottomFrameLayoutId = View.generateViewId()
//
//        bottomFrameLayout.id = presenter.bottomFrameLayoutId
//
//        parentConstraint.addView(bottomFrameLayout)
//
//        val constraintSet = ConstraintSet()
//
//        constraintSet.clone(parentConstraint)
//
//        constraintSet.connect(presenter.bottomFrameLayoutId, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM )
//
//        constraintSet.connect(presenter.bottomFrameLayoutId, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
//
//        constraintSet.connect(presenter.bottomFrameLayoutId, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
//
//        constraintSet.clear(coordinator.id, ConstraintSet.BOTTOM)
//
//        constraintSet.connect(coordinator.id, ConstraintSet.BOTTOM, presenter.bottomFrameLayoutId, ConstraintSet.TOP)
//
//        constraintSet.applyTo(parentConstraint)
//    }
//
//    override fun showAddTaskFragment() {
//        createFrameLayout()
//
//        supportFragmentManager.beginTransaction().replace(presenter.bottomFrameLayoutId, AddTaskFragment(), AddTaskFragment.TAG).commit()
//
//        app_bar.setExpanded(false)
//    }
//
//    override fun hideAddTaskFragment() {
//        val bottomFragment = supportFragmentManager.findFragmentByTag(AddTaskFragment.TAG)
//
//        if(bottomFragment != null) supportFragmentManager.beginTransaction().remove(bottomFragment).commit()
//
//        parentConstraint.removeView(findViewById(presenter.bottomFrameLayoutId))
//
//        val constraintSet = ConstraintSet()
//        constraintSet.clone(parentConstraint)
//
//        constraintSet.clear(coordinator.id, ConstraintSet.BOTTOM)
//
//        constraintSet.connect(coordinator.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
//
//        constraintSet.applyTo(parentConstraint)
//
//        presenter.bottomFrameLayoutId = 0
//
//        fab.show()
//    }
//
    override fun setToolbarBackground(background: Int) {
        toolbarBackground.setImageResource(background)
    }
//
//    override fun onBackPressed() {
//        if(presenter.bottomFrameLayoutId != 0) {
//            hideAddTaskFragment()
//            presenter.enumAddTaskFragmentHide()
//        }
//        else super.onBackPressed()
//    }
//
//    private fun setMenu() {
//        toolbar.inflateMenu(R.menu.main)
//
//        toolbar.setOnMenuItemClickListener {
//            if(it.itemId == R.id.check) presenter.setFinishedTasksVisibility()
//            false
//        }
//    }
//
//    override fun updateIconCheckFinishedItemsVisibility(drawable: Int) {
//        toolbar.menu.getItem(0).icon = resources.getDrawable(drawable, null)
//    }

    override fun onDestroy() {
        super.onDestroy()
        if(isFinishing) Toothpick.closeScope(DI.MAIN_ACTIVITY_SCOPE)
    }
}