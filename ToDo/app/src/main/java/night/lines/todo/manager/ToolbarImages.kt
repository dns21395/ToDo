package night.lines.todo.manager

import night.lines.todo.R
import java.util.*

class ToolbarImages  {
    private val images = arrayOf(
            R.drawable.background1,
            R.drawable.background2,
            R.drawable.background3,
            R.drawable.background4,
            R.drawable.background5,
            R.drawable.background6,
            R.drawable.background7,
            R.drawable.background8,
            R.drawable.background9
    )

    fun getBackground(): Int = images[Random().nextInt(images.size)]
}