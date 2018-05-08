package night.lines.todo.ui.main.addtask

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import kotlinx.android.synthetic.main.fragment_add_task.*
import night.lines.todo.R
import night.lines.todo.databinding.FragmentAddTaskBinding
import night.lines.todo.util.KeyboardUtils
import night.lines.todo.presentation.main.addtask.AddTaskFragmentViewModel
import night.lines.todo.presentation.main.addtask.AddTaskNavigator
import night.lines.todo.toothpick.main.Main
import night.lines.todo.ui.base.BaseFragment
import org.jetbrains.anko.support.v4.toast
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class AddTaskFragment : BaseFragment<FragmentAddTaskBinding, AddTaskFragmentViewModel>(), AddTaskNavigator {

    override val layoutRes: Int = R.layout.fragment_add_task

    @Inject lateinit var addTaskViewModel: AddTaskFragmentViewModel

    companion object {
        val TAG = "AddTaskFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addTaskViewModel.navigator = this
    }

    override fun getViewModel(): AddTaskFragmentViewModel = addTaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated")

        context?.let { KeyboardUtils.showSoftInput(textTask, it) }

        textTask.setOnEditorActionListener{ textView, i, keyEvent ->
            if(i == KeyEvent.KEYCODE_ENDCALL) addTask()
            true
        }

        addButton.setOnClickListener {
           addTask()
        }
    }

    override fun performDependencyInjection() {
        Toothpick.openScope(Main::class.java).apply {
            Toothpick.inject(this@AddTaskFragment, this)
        }
    }

    private fun addTask() {
        if (textTask.text.toString().isNotEmpty()) {
            addTaskViewModel.onAddTaskButtonClicked(textTask.text.toString())
            textTask.text.clear()
        } else {
            toast(R.string.text_add_task_empty)

        }
    }

    override fun getBindingVariable(): Int = BR.viewModel
}