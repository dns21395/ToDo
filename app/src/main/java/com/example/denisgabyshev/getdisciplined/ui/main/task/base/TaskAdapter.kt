package com.example.denisgabyshev.getdisciplined.ui.main.task.base

import android.content.Context
import android.graphics.Paint
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
import org.jetbrains.anko.imageResource
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


        holder.itemView.setOnLongClickListener {
            onStartDragListener.onStartDrag(holder)
            false
        }

        holder.itemView.status.setOnClickListener {
            statusButtonClicked(holder, position)
        }
    }

    private fun statusButtonClicked(holder: TaskViewHolder, position: Int) {
        tasks[position].status = !tasks[position].status

        dataManager.updateTaskStatus(tasks[position])

        holder.setStatus(tasks[position])

    }

    override fun getItemCount(): Int = tasks.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
            TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))

    fun setArray(taskArray: ArrayList<Task>) {

        if (tasks.size > 0) {
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
        Log.d(TAG, "OnItemSwipe ${tasks[pos]}")
        notifyItemRemoved(pos)
        dataManager.deleteTask(tasks[pos])
        tasks.removeAt(pos)

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
        (viewHolder as TaskViewHolder).changeBackground(R.color.colorPrimaryLight)
    }

    fun dragStopped(viewHolder: RecyclerView.ViewHolder) {
        (viewHolder as TaskViewHolder).changeBackground(android.R.color.white)

    }

    private fun moveItem(oldPos: Int, newPos: Int) {
        notifyItemMoved(oldPos, newPos)

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

    lateinit var taskClass: Task


    fun bind(_task: Task) = with(itemView) {
        task.text = _task.task

        taskClass = _task

        setStatus(taskClass)
    }

    fun setStatus(_task: Task) = with(itemView) {
        taskClass = _task

        if(taskClass.status) statusTrue() else statusFalse()
    }

     private fun statusTrue() = with(itemView) {
        task.paintFlags = task.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        status.imageResource = R.drawable.checkbox_marked_circle
    }

    private fun statusFalse() = with(itemView) {
        task.paintFlags = task.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

        status.imageResource = R.drawable.checkbox_blank_circle_outline

    }

    fun changeBackground(color: Int) = with(itemView)  {
        parentRL.backgroundColor = ContextCompat.getColor(context, color)
    }
}