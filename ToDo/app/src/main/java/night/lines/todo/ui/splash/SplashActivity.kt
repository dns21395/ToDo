package night.lines.todo.ui.splash

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import night.lines.todo.mvp.presenters.SplashPresenter
import night.lines.todo.mvp.views.SplashView
import night.lines.todo.ui.main.MainActivity

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class SplashActivity : MvpAppCompatActivity(), SplashView {

    @InjectPresenter
    lateinit var splashPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))

    }
}