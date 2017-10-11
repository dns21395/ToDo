package com.example.denisgabyshev.getdisciplined.ui.main.task.today.toolbar

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.denisgabyshev.getdisciplined.R

/**
 * Created by denisgabyshev on 08/10/2017.
 */
class ToolbarHeaderBehaviour(val mContext: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<ToolbarHeader>(mContext, attrs) {


    private var mStartMarginLeft: Int = 0
    private var mEndMarginLeft: Int = 0
    private var mMarginRight: Int = 0
    private var mStartMarginBottom: Int = 0
    private var mTitleStartSize: Float = 0.toFloat()
    private var mTitleEndSize: Float = 0.toFloat()
    private var isHide: Boolean = false

    private val TAG = "ToolbarHeaderBehaviour"

    private fun getToolbarHeight(context: Context?): Int {
        var result = 0
        val tv = TypedValue()
        if (context!!.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return result
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: ToolbarHeader?, dependency: View?): Boolean =
            dependency is AppBarLayout

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: ToolbarHeader, dependency: View?): Boolean {
        shouldInitProperties()

        textAnimationControl(child, dependency)

        return true
    }

    private fun textAnimationControl(child: ToolbarHeader, dependency: View?) {
        val maxScroll = (dependency as AppBarLayout).totalScrollRange
        val percentage = Math.abs(dependency.getY()) / maxScroll.toFloat()

        var childPosition = dependency.height + dependency.y - child.height - (getToolbarHeight(mContext) - child.height) * percentage / 2
        childPosition -= mStartMarginBottom * (1f - percentage)


        val params = child.layoutParams as CoordinatorLayout.LayoutParams

        if(Math.abs(dependency.y) >= maxScroll / 2) {
            val layoutPercentage = (Math.abs(dependency.y) - (maxScroll / 2)) / Math.abs(maxScroll / 2)
            params.leftMargin = ((layoutPercentage * mEndMarginLeft).toInt() + mStartMarginLeft)
            child.setTextSize(getTranslationOffset(mTitleStartSize, mTitleEndSize, layoutPercentage))
        } else {
            params.leftMargin = mStartMarginLeft
        }
        params.rightMargin = mMarginRight

        child.layoutParams = params
        child.y = childPosition
    }

    private fun getTranslationOffset(expandedOffset: Float, collapsedOffset: Float, ratio: Float): Float =
            expandedOffset + ratio * (collapsedOffset - expandedOffset)

    private fun shouldInitProperties() {
        if (mStartMarginLeft == 0) {
            mStartMarginLeft = mContext!!.resources.getDimensionPixelOffset(R.dimen.header_view_start_margin_left)
        }

        if (mEndMarginLeft == 0) {
            mEndMarginLeft = mContext!!.resources.getDimensionPixelOffset(R.dimen.header_view_end_margin_left)
        }

        if (mStartMarginBottom == 0) {
            mStartMarginBottom = mContext!!.resources.getDimensionPixelOffset(R.dimen.header_view_start_margin_bottom)
        }

        if (mMarginRight == 0) {
            mMarginRight = mContext!!.resources.getDimensionPixelOffset(R.dimen.header_view_end_margin_right)
        }

        if (mTitleStartSize == 0f) {
            mTitleEndSize = mContext!!.resources.getDimensionPixelSize(R.dimen.header_view_end_text_size).toFloat()
        }

        if (mTitleStartSize == 0f) {
            mTitleStartSize = mContext!!.resources.getDimensionPixelSize(R.dimen.header_view_start_text_size).toFloat()
        }
    }

}