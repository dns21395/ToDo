package com.example.denisgabyshev.getdisciplined.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.denisgabyshev.getdisciplined.App
import com.example.denisgabyshev.getdisciplined.R
import com.example.denisgabyshev.getdisciplined.data.DataManager
import com.example.denisgabyshev.getdisciplined.data.db.model.ListId
import kotlinx.android.synthetic.main.listid_item.view.*
import javax.inject.Inject

/**
 * Created by denisgabyshev on 03/11/2017.
 */
class ListAdapter(val context: Context) :
    RecyclerView.Adapter<ListViewHolder>() {

    lateinit var callback: Callback

    @Inject lateinit var dataManager: DataManager

    private var listIds = ArrayList<ListId>()

    init {
        (context as App).component().inject(this)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        if(position < 2) {
            when(position) {
                0 -> {
                    holder.bind2(context.resources.getString(R.string.myday), R.drawable.myday)
                }
                1 -> {
                    holder.bind2(context.resources.getString(R.string.todo), R.drawable.todo)
                }
            }
        } else {
            holder.bind(listIds[position])
        }

        holder.itemView.setOnClickListener {
            if(position < 2) {
                callback.clickedTodayOrToDoItem(position)
            } else {
                callback.clickedNavigationItem(listIds[position])
            }
        }
    }

    override fun getItemCount(): Int = listIds.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder =
            ListViewHolder(LayoutInflater.from(context).inflate(R.layout.listid_item, parent, false))

    fun updateArray(array: ArrayList<ListId>) {
        listIds = array

        listIds.add(0, ListId(0, context.resources.getString(R.string.todo), 0))
        listIds.add(0, ListId(0, context.resources.getString(R.string.myday), 0))

        notifyDataSetChanged()
    }


    interface Callback {
        fun clickedTodayOrToDoItem(position: Int)

        fun clickedNavigationItem(listId: ListId)
    }

}

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var _listId: ListId

    fun bind(listId: ListId) = with(itemView) {
        text.text = listId.name

        _listId = listId
    }

    fun bind2(name: String, imageId: Int) = with(itemView) {
        image.setImageResource(imageId)
        text.text = name
    }
}