package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.db.model.Task
import kotlinx.android.synthetic.main.task_item.view.*
import org.jetbrains.anko.backgroundColor

/**
 * Created by denisgabyshev on 04/10/2017.
 */
class TaskAdapter(val appBar: AppBarLayout, val recyclerView: RecyclerView) :
        RecyclerView.Adapter<TaskViewHolder>(), ItemTouchHelperAdapter, OnStartDragListener {


    private var callback = ItemTouchHelperCallback(this)
    private var itemTouchHelper = ItemTouchHelper(callback)
    private var onStartDragListener: OnStartDragListener = this

    private var itemDragger = false


    init {
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private val TAG = "TaskAdapter"

    private var tasks = ArrayList<Task>()


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])

        holder.itemView?.setOnClickListener {
            Log.d(TAG, "Clicked : ${tasks[position]}")
        }

        holder.itemView.setOnLongClickListener {

            onStartDragListener.onStartDrag(holder)
            itemDragger = true
            false
        }
    }

    override fun getItemCount(): Int = tasks.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
            TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))

    fun setArray(taskArray: ArrayList<Task>) {
        if(tasks.size > 0) {
            appBar.setExpanded(false)
        }

        tasks = taskArray

        notifyDataSetChanged()

        recyclerView.smoothScrollToPosition(itemCount)
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
        (viewHolder as TaskViewHolder).changeBackground(R.color.selected)
    }

    fun dragStopped(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as TaskViewHolder).changeBackground(android.R.color.white)

    }

    private fun moveItem(oldPos: Int, newPos: Int) {
        notifyItemMoved(oldPos, newPos)
    }
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val TAG = "TaskViewHolder"


    fun bind(_task: Task) = with(itemView) {
        task.text = _task.task
    }

    fun changeBackground(color: Int) = with(itemView)  {
        parentRL.backgroundColor = ContextCompat.getColor(context, color)
    }
}