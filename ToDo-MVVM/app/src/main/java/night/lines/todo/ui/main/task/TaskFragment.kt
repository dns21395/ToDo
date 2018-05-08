package night.lines.todo.ui.main.task

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.android.databinding.library.baseAdapters.BR
import kotlinx.android.synthetic.main.fragment_task.*
import night.lines.todo.R
import night.lines.todo.databinding.FragmentTaskBinding
import night.lines.todo.domain.model.Task
import night.lines.todo.presentation.main.task.TaskFragmentViewModel
import night.lines.todo.presentation.main.task.TaskNavigator
import night.lines.todo.toothpick.DI
import night.lines.todo.toothpick.main.Main
import night.lines.todo.ui.base.BaseFragment
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class TaskFragment : BaseFragment<FragmentTaskBinding, TaskFragmentViewModel>(), TaskNavigator {
    private val TAG = "TaskFragment"

    private lateinit var adapter: TaskAdapter
    private val layoutManager = LinearLayoutManager(context)

    @Inject lateinit var taskFragmentViewModel: TaskFragmentViewModel

    override val layoutRes = R.layout.fragment_task

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = TaskAdapter(context!!, recyclerView)
        adapter.viewModel = taskFragmentViewModel
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, layoutManager.orientation))

        taskFragmentViewModel.onViewPrepared()
    }

    override fun updateTaskArray(array: ArrayList<Task>) {
        adapter.updateArray(array)
    }

    override fun scrollToEnd() {
        adapter.smoothScrollToPosition()
    }

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getViewModel(): TaskFragmentViewModel = taskFragmentViewModel

    override fun performDependencyInjection() {
        Toothpick.openScope(Main::class.java).apply {
            Toothpick.inject(this@TaskFragment, this)
        }
    }
}