package night.lines.todo.model

import android.content.Context
import javax.inject.Inject

/**
 * Created by denisgabyshev on 19/03/2018.
 */
class AppTestManager @Inject constructor(private val context: Context) {

    fun getPackageName(): String = context.packageName

}