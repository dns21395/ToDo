package night.lines.todo.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_task.view.*
import night.lines.todo.R
import night.lines.todo.database.model.Task
import night.lines.todo.presentation.main.task.TaskPresenter
import org.jetbrains.anko.imageResource

/**
 * Created by denisgabyshev on 20/03/2018.
 */
class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    lateinit var presenter: TaskPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder
        = TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))

    override fun getItemCount(): Int = presenter.getTaskArrayItemCount()

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(presenter.getTaskByPosition(position))
    }

    fun updateArray(array: ArrayList<Task>) {
        presenter.updateTaskArray(array)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) = with(itemView){
            taskName.text = task.taskName

            when(task.isDone) {
                true -> statusButton.imageResource = R.drawable.checkbox_marked_circle
                false -> statusButton.imageResource = R.drawable.checkbox_blank_circle_outline
            }

            statusButton.setOnClickListener {
                task.isDone = !task.isDone
                presenter.onStatusButtonClick(task)
            }
        }
    }
}