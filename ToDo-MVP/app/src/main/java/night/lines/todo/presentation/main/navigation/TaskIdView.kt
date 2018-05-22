package night.lines.todo.presentation.main.navigation

import com.arellomobile.mvp.MvpView

interface TaskIdView : MvpView {
    fun showToastListAdded(listName: String)

    fun showToastEditTextEmpty()

    fun dismiss()
}