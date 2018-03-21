package night.lines.todo.ui.main.task

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import night.lines.todo.R
import night.lines.todo.ui.main.TaskAdapter

/**
 * Created by denisgabyshev on 21/03/2018.
 */
class TaskAdapterSwipeCallback(private val context: Context, private val adapter: TaskAdapter) : ItemTouchHelper.Callback() {
    private var isSwipe = false

    override fun isItemViewSwipeEnabled(): Boolean = isSwipe

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int
        = makeMovementFlags(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT)

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        val itemView = viewHolder.itemView

        if(dX > 0) drawViewHolder(c, itemView, dX, itemView.left)
        if(dX < 0) drawViewHolder(c, itemView, dX, itemView.right)


        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawViewHolder(c: Canvas?, itemView: View, dX: Float, itemViewDirection: Int) {
        drawBackground(c, dX, itemViewDirection, itemView)
        drawIcon(c, itemViewDirection, itemView)
    }

    private fun drawBackground(c: Canvas?, dX: Float, itemViewDirection: Int, itemView: View) {
        val background = ColorDrawable()
        background.color =  ContextCompat.getColor(context, R.color.swipe_item)

        when(itemViewDirection) {
            itemView.left -> background.setBounds(itemViewDirection, itemView.top, itemViewDirection + dX.toInt(), itemView.bottom)
            itemView.right -> background.setBounds(itemViewDirection + dX.toInt(), itemView.top, itemViewDirection, itemView.bottom)
        }
        background.draw(c)
    }

    private fun drawIcon(c: Canvas?, itemViewDirection: Int, itemView: View) {
        val icon: Drawable? = ContextCompat.getDrawable(context, R.drawable.delete)

        val intrinsicWidth = icon?.intrinsicWidth ?: 0
        val intrinsicHeight = icon?.intrinsicHeight ?: 0

        // Calculate position icon
        val itemHeight = itemView.bottom - itemView.top
        val deleteIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
        val deleteIconMargin = (itemHeight - intrinsicHeight) / 2
        val deleteIconLeft =
                if(itemViewDirection == itemView.right) itemViewDirection - deleteIconMargin - intrinsicWidth
                else itemViewDirection + deleteIconMargin
        val deleteIconRight =
                if(itemViewDirection == itemView.right) itemViewDirection - deleteIconMargin
                else itemViewDirection + deleteIconMargin + intrinsicWidth
        val deleteIconBottom = deleteIconTop + intrinsicHeight

        // Draw icon
        icon?.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom)
        icon?.draw(c)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?)
            : Boolean = false


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.removeAt(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if(viewHolder is TaskAdapter.TaskViewHolder) {
            viewHolder.onItemSelected()
            isSwipe = true
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
        if(viewHolder is TaskAdapter.TaskViewHolder) {
            viewHolder.onItemClear()
            isSwipe = false
        }
        super.clearView(recyclerView, viewHolder)
    }
}