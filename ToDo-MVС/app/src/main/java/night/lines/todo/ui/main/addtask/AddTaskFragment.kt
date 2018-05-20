package night.lines.todo.ui.main.addtask

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_add_task.*
import night.lines.todo.R
import night.lines.todo.util.KeyboardUtils
import night.lines.todo.domain.interactor.main.AddTaskUseCase
import night.lines.todo.domain.model.Task
import night.lines.todo.toothpick.addtask.AddTaskScope
import night.lines.todo.toothpick.main.MainScope
import night.lines.todo.ui.base.BaseFragment
import night.lines.todo.ui.main.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import org.jetbrains.anko.support.v4.toast
import toothpick.Toothpick
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class AddTaskFragment : BaseFragment() {

    @Inject lateinit var compositeDisposable: CompositeDisposable
    @Inject lateinit var schedulerProvider: SchedulerProvider
    @Inject lateinit var taskFragmentRelay: TaskFragmentRelay
    @Inject lateinit var addTaskUseCase: AddTaskUseCase

    companion object {
        val TAG = "AddTaskFragment"
    }

    override val layoutRes = R.layout.fragment_add_task

    override fun inject() {
        Toothpick.openScopes(MainScope::class.java, AddTaskScope::class.java).apply {
            Toothpick.inject(this@AddTaskFragment, this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        context?.let { KeyboardUtils.showSoftInput(textTask, it) }

        textTask.setOnEditorActionListener{ textView, i, keyEvent ->
            if(i == KeyEvent.KEYCODE_ENDCALL) addTask()
            true
        }

        addButton.setOnClickListener {
           addTask()
        }
    }

    private fun addTask() {
        if (textTask.text.toString().isNotEmpty()) {
            addTask(textTask.text.toString())
            textTask.text.clear()
        } else {
            toast(R.string.text_add_task_empty)
        }
    }

    fun addTask(taskName: String) {
        compositeDisposable.add(
                addTaskUseCase.execute(Task(0, taskName, Date().time))
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            taskFragmentRelay.callTaskFragmentAction(TaskFragmentRelay.EnumTaskFragment.ITEM_ADDED)
                        }
        )
    }

    override fun onDestroy() {
        Toothpick.closeScope(AddTaskScope::class.java)
        super.onDestroy()
    }


}