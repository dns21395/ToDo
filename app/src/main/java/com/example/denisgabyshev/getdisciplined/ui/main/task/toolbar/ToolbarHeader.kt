package com.example.denisgabyshev.getdisciplined.ui.main.task.toolbar

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main_header.view.*

/**
 * Created by denisgabyshev on 07/10/2017.
 */
class ToolbarHeader : LinearLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)



    fun setTextSize(size: Float) {
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }

    fun setVisibility(visibility: Boolean) {
        if(visibility) {
            title.visibility = View.VISIBLE
            subtitle.visibility = View.VISIBLE
        } else {
            title.visibility = View.INVISIBLE
            subtitle.visibility = View.INVISIBLE
        }
    }

}