package night.lines.todo.ui.main.task

import android.content.Context
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_task.view.*
import night.lines.todo.R
import night.lines.todo.domain.model.Task
import night.lines.todo.presentation.main.task.TaskPresenter
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.imageResource
import javax.inject.Inject

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class TaskAdapter @Inject constructor(context: Context) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    lateinit var presenter: TaskPresenter

    private var recyclerView: RecyclerView? = null

    private val swipeCallback = TaskAdapterSwipeCallback(context, this)
    private val itemTouchHelper = ItemTouchHelper(swipeCallback)


    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder
        = TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))

    override fun getItemCount(): Int = presenter.getTaskArrayItemCount()

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(presenter.getTaskByPosition(position))
    }

    fun updateArray(array: ArrayList<Task>) {
        presenter.updateTaskArray(array)
        notifyDataSetChanged()
        if(presenter.isAddTaskFragmentVisible) smoothScrollToPosition()
    }

    fun removeAt(position: Int) {
        presenter.onItemSwiped(presenter.getTaskByPosition(position), {notifyItemRemoved(position)})
    }

    fun smoothScrollToPosition() {
        recyclerView?.smoothScrollToPosition(presenter.getTaskArrayItemCount())
    }

    inner class TaskViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView?.setOnLongClickListener {
                itemTouchHelper.startSwipe(this)
                false
            }
        }

        fun bind(task: Task) = with(itemView){
            taskName.text = task.taskName

            when(task.isDone) {
                false -> {
                    taskName.paintFlags = taskName.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    statusButton.imageResource = R.drawable.checkbox_blank_circle_outline
                }
                true -> {
                    taskName.paintFlags = taskName.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    statusButton.imageResource = R.drawable.checkbox_marked_circle
                }
            }

            statusButton.setOnClickListener {
                task.isDone = !task.isDone
                presenter.onStatusButtonClick(task)
            }
        }

        private fun changeBackground(color: Int) = with(itemView)  {
            itemView.backgroundColor = ContextCompat.getColor(context, color)
        }

        fun onItemSelected() {
            changeBackground(R.color.item_touch)
        }

        fun onItemClear() {
            changeBackground(android.R.color.white)
        }
    }
}