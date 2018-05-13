package night.lines.todo.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import night.lines.todo.controller.splash.SplashController
import night.lines.todo.toothpick.DI
import night.lines.todo.toothpick.splash.Splash
import night.lines.todo.ui.main.MainActivity
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class SplashActivity : AppCompatActivity() {

    @Inject lateinit var splashController: SplashController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun inject() {
        Toothpick.openScopes(DI.APP_SCOPE, Splash::class.java).apply {
            Toothpick.inject(this@SplashActivity, this)
        }
    }
}