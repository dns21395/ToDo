package night.lines.todo.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import night.lines.todo.ui.main.MainActivity

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class SplashController : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

//        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun inject() {

    }
}