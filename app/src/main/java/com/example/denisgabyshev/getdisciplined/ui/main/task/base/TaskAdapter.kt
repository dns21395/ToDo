package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.App
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import kotlinx.android.synthetic.main.task_item.view.*
import org.jetbrains.anko.backgroundColor
import java.util.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 04/10/2017.
 */
class TaskAdapter(val appBar: AppBarLayout, val recyclerView: RecyclerView, val context: Context) :
        RecyclerView.Adapter<TaskViewHolder>(), ItemTouchHelperAdapter, OnStartDragListener {

    @Inject lateinit var dataManager: DataManager


    private var callback = ItemTouchHelperCallback(this)
    private var itemTouchHelper = ItemTouchHelper(callback)
    private var onStartDragListener: OnStartDragListener = this

    private var isDragging = false

    init {
        itemTouchHelper.attachToRecyclerView(recyclerView)

        (context as App).component().inject(this)
    }

    private val TAG = "TaskAdapter"

    private var tasks = ArrayList<Task>()


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])

        holder.itemView?.setOnClickListener {
            Log.d(TAG, "Clicked : ${tasks[position]}")
        }

        holder.itemView.setOnTouchListener { v, event ->
            if(event.action == MotionEvent.ACTION_DOWN) onStartDragListener.onStartDrag(holder)
            false
        }
    }

    override fun getItemCount(): Int = tasks.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
            TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))

    fun setArray(taskArray: ArrayList<Task>) {
        if(!isDragging) {
            if (tasks.size > 0) {
                appBar.setExpanded(false)
            }

            tasks = taskArray

            notifyDataSetChanged()

            recyclerView.smoothScrollToPosition(itemCount)
        }
    }

    override fun onItemMove(oldPos: Int, newPos: Int): Boolean {
        moveItem(oldPos, newPos)
        return true
    }

    override fun onItemSwipe(pos: Int) {
        notifyItemRemoved(pos)
        tasks.removeAt(pos)

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
        isDragging = true
        (viewHolder as TaskViewHolder).changeBackground(R.color.selected)
    }

    fun dragStopped(viewHolder: RecyclerView.ViewHolder) {
        isDragging = false
        (viewHolder as TaskViewHolder).changeBackground(android.R.color.white)

    }

    private fun moveItem(oldPos: Int, newPos: Int) {
        notifyItemMoved(oldPos, newPos)

        Log.d(TAG, "move item task1=${tasks[oldPos]}\ntask2=${tasks[newPos]}\n")



        val orderForOld = tasks[newPos].taskOrder
        val orderFolNew = tasks[oldPos].taskOrder

        dataManager.updateTaskOrder(tasks[oldPos], orderForOld)
        dataManager.updateTaskOrder(tasks[newPos], orderFolNew)

        tasks[oldPos].taskOrder = orderForOld
        tasks[newPos].taskOrder = orderFolNew

        Collections.swap(tasks, oldPos, newPos)
    }
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val TAG = "TaskViewHolder"


    fun bind(_task: Task) = with(itemView) {
        task.text = "${_task.task} ${_task.taskOrder}"
    }

    fun changeBackground(color: Int) = with(itemView)  {
        parentRL.backgroundColor = ContextCompat.getColor(context, color)
    }
}