package night.lines.todo.ui.main.navigation

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import night.lines.todo.R
import night.lines.todo.presentation.main.navigation.TaskIdPresenter
import night.lines.todo.presentation.main.navigation.TaskIdScope
import night.lines.todo.presentation.main.navigation.TaskIdView
import night.lines.todo.toothpick.main.MainScope
import night.lines.todo.ui.base.BaseDialog
import toothpick.Toothpick

class TaskIdDialog : BaseDialog(), TaskIdView {

    val TAG = "TaskDialog"

    @InjectPresenter lateinit var presenter: TaskIdPresenter

    @ProvidePresenter
    fun providePresenter(): TaskIdPresenter =
            Toothpick.openScopes(MainScope::class.java, TaskIdScope::class.java).apply {
                Toothpick.inject(this@TaskIdDialog, this)
            }.getInstance(TaskIdPresenter::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_add_task_id, container, false)

    fun show(fragmentManager: FragmentManager) { super.show(fragmentManager, TAG) }
}