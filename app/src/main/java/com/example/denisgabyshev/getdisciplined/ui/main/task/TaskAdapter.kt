package com.example.denisgabyshev.getdisciplined.ui.main.task

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import kotlinx.android.synthetic.main.task_item.view.*

/**
 * Created by denisgabyshev on 04/10/2017.
 */
class TaskAdapter(val linearLayoutManager: LinearLayoutManager) : RecyclerView.Adapter<TaskViewHolder>() {
    private val TAG = "TaskAdapter"

    private var tasks = ArrayList<Task>()

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))

    fun setArray(taskArray: ArrayList<Task>) {
        Log.d(TAG, "${taskArray}")
        tasks = taskArray
        notifyDataSetChanged()

        linearLayoutManager.scrollToPosition(0)


    }

}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(_task: Task) = with(itemView) {
        task.text = _task.task
    }
}