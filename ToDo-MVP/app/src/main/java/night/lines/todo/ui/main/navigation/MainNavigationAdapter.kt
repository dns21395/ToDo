package night.lines.todo.ui.main.navigation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject
import android.content.Context
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.item_taskid.view.*
import night.lines.todo.R
import night.lines.todo.presentation.main.MainPresenter

class MainNavigationAdapter @Inject constructor(private val context: Context) :  RecyclerView.Adapter<MainNavigationAdapter.TaskIDViewHolder>() {

    lateinit var presenter: MainPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskIDViewHolder =
            TaskIDViewHolder(LayoutInflater.from(context).inflate(R.layout.item_taskid, parent, false))

    override fun getItemCount(): Int = presenter.getTaskIDArrayItemCount() + 1

    override fun onBindViewHolder(holder: TaskIDViewHolder, position: Int) {
        when(position) {
            0 -> holder.bindFirst()
            else -> holder.bind(position - 1)
        }
    }

    inner class TaskIDViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) = with(itemView) {
            val taskID = presenter.getTaskIDByPosition(position)

            text.text = taskID.name
        }

        fun bindFirst() = with(itemView) {
            text.text = context.getString(R.string.todo)
            image.setImageResource(R.drawable.myday)
        }
    }
}