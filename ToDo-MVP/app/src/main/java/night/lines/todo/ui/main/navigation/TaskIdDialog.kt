package night.lines.todo.ui.main.navigation

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.dialog_add_task_id.*
import night.lines.todo.R
import night.lines.todo.presentation.main.navigation.TaskIdPresenter
import night.lines.todo.toothpick.main.navigation.TaskIdScope
import night.lines.todo.presentation.main.navigation.TaskIdView
import night.lines.todo.toothpick.main.MainScope
import night.lines.todo.toothpick.main.navigation.TaskIdModule
import night.lines.todo.ui.base.BaseDialog
import org.jetbrains.anko.support.v4.toast
import toothpick.Toothpick

class TaskIdDialog : BaseDialog(), TaskIdView {

    val TAG = "TaskDialog"

    @InjectPresenter lateinit var presenter: TaskIdPresenter

    @ProvidePresenter
    fun providePresenter(): TaskIdPresenter =
            Toothpick.openScopes(MainScope::class.java, TaskIdScope::class.java).apply {
                installModules(TaskIdModule())
                Toothpick.inject(this@TaskIdDialog, this)
            }.getInstance(TaskIdPresenter::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        positive.setOnClickListener { presenter.onPositiveButtonClicked(taskIdName.text.toString()) }

        negative.setOnClickListener { dismiss() }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_add_task_id, container, false)

    fun show(fragmentManager: FragmentManager) { super.show(fragmentManager, TAG) }

    override fun showToastListAdded(listName: String) { toast(getString(R.string.list_added, listName)) }

    override fun showToastEditTextEmpty() { toast(R.string.text_add_task_empty) }





}