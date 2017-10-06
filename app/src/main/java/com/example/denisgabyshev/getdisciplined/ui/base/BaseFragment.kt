package com.example.denisgabyshev.getdisciplined.ui.base

import android.content.Context
import android.graphics.Typeface
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatDrawableManager
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.example.denisgabyshev.getdisciplined.utils.KeyboardUtils
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

/**
 * Created by denisgabyshev on 10/09/2017.
 */
abstract class BaseFragment : Fragment(), MvpView {
    lateinit var activity: BaseActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is BaseActivity) {
            activity = context
        }
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun hideKeyboard() {
        KeyboardUtils.hideSoftInput(activity)
    }

    override fun ImageView.imageBackground(drawable: Int) {
        setImageDrawable(AppCompatDrawableManager.get().getDrawable(context, drawable))
    }

    override fun transparentStatusBar() {
       // activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE ; View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun TextView.setFont(fontPath: String) {
        typeface = Typeface.createFromAsset(context.assets, fontPath)
    }
}