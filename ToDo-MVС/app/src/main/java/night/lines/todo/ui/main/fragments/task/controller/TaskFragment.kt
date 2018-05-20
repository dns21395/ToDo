package night.lines.todo.ui.main.fragments.task.controller

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_task.*
import night.lines.todo.R
import night.lines.todo.domain.model.Task
import night.lines.todo.domain.interactor.main.GetTasksUseCase
import night.lines.todo.domain.interactor.main.RemoveTaskUseCase
import night.lines.todo.domain.interactor.main.UpdateTaskUseCase
import night.lines.todo.domain.repository.PreferencesRepository
import night.lines.todo.ui.base.BaseFragment
import night.lines.todo.ui.main.fragments.addtask.AddTaskFragmentRelay
import night.lines.todo.ui.main.fragments.task.TaskAdapter
import night.lines.todo.ui.main.fragments.task.TaskFragmentRelay
import night.lines.todo.util.SchedulerProvider
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class TaskFragment : BaseFragment() {


    private val TAG = "TaskFragment"

    private lateinit var adapter: TaskAdapter
    private lateinit var getTasksDisposable: Disposable
    private val layoutManager = LinearLayoutManager(context)

    @Inject lateinit var compositeDisposable: CompositeDisposable
    @Inject lateinit var schedulerProvider: SchedulerProvider
    @Inject lateinit var addTaskFragmentRelay: AddTaskFragmentRelay
    @Inject lateinit var taskFragmentRelay: TaskFragmentRelay
    @Inject lateinit var preferencesRepository: PreferencesRepository
    @Inject lateinit var updateTaskUseCase: UpdateTaskUseCase
    @Inject lateinit var removeTaskUseCase: RemoveTaskUseCase
    @Inject lateinit var getTasksUseCase: GetTasksUseCase

    override val layoutRes = R.layout.fragment_task

    var isAddTaskFragmentVisible = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = TaskAdapter(context!!, recyclerView)
        adapter.taskFragment = this
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, layoutManager.orientation))

        getTasksDisposable = updateGetTasksDisposable()

        compositeDisposable.add(getTasksDisposable)

        compositeDisposable.add(showOrHideFinishedItems())

        compositeDisposable.add(isAddTaskFragmentVisible())
    }

    fun scrollToEnd() {
        adapter.smoothScrollToPosition()
    }

    private fun isAddTaskFragmentVisible(): Disposable
            = addTaskFragmentRelay.addTaskFragmentState
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe {
                isAddTaskFragmentVisible = when(it) {
                    AddTaskFragmentRelay.EnumAddTaskFragment.SHOW -> {
                        scrollToEnd()
                        true
                    }
                    else -> false
                }
            }

    private fun showOrHideFinishedItems(): Disposable
            = taskFragmentRelay.taskFragmentState
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe {
                when(it) {
                    TaskFragmentRelay.EnumTaskFragment.FINISHED_ITEMS_VISIBILITY_UPDATED -> {
                        getTasksDisposable.dispose()
                        getTasksDisposable = updateGetTasksDisposable()
                        compositeDisposable.add(getTasksDisposable)
                    }
                    else -> scrollToEnd()
                }
            }

    private fun updateGetTasksDisposable(): Disposable
            = preferencesRepository.getFinishedTasksVisibility().toFlowable(BackpressureStrategy.BUFFER)
            .flatMap {
                getTasksUseCase.execute(it)
            }
            .compose(schedulerProvider.ioToMainFlowableScheduler())
            .subscribe {
                adapter.updateArray(it)
            }

    fun onStatusButtonClick(task: Task) {
        compositeDisposable.add(
                updateTaskUseCase.execute(task)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe()
        )
    }

    fun onItemSwiped(task: Task, function: () -> Unit) {
        compositeDisposable.add(
                removeTaskUseCase.execute(task)
                        .compose(schedulerProvider.ioToMainObservableScheduler())
                        .subscribe {
                            function.invoke()
                        }
        )
    }
}