package night.lines.todo.ui.main.addtask

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fragment_add_task.*
import night.lines.todo.R
import night.lines.todo.databinding.FragmentAddTaskBinding
import night.lines.todo.util.KeyboardUtils
import night.lines.todo.presentation.main.addtask.AddTaskPresenter
import night.lines.todo.presentation.main.addtask.AddTaskView
import night.lines.todo.toothpick.DI
import night.lines.todo.ui.base.BaseFragment
import org.jetbrains.anko.support.v4.toast
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class AddTaskFragment : Fragment(), AddTaskView {

    @InjectPresenter lateinit var presenter: AddTaskPresenter

    lateinit var addTaskBinding: FragmentAddTaskBinding
    @Inject lateinit var addTaskViewModel: AddTaskViewModel

    companion object {
        val TAG = "AddTaskFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Toothpick.openScope(DI.MAIN_ACTIVITY_SCOPE).apply {
            Toothpick.inject(this@AddTaskFragment, this)
        }
        addTaskBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container, false)
        addTaskBinding.viewModel = addTaskViewModel
        return addTaskBinding.root
    }

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


    private fun addTask() {
        if (textTask.text.toString().isNotEmpty()) {
            presenter.onAddTaskButtonClicked(textTask.text.toString())
            textTask.text.clear()
        } else {
            toast(R.string.text_add_task_empty)

        }
    }


}