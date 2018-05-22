package night.lines.todo.ui.splash

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import night.lines.todo.presentation.splash.SplashPresenter
import night.lines.todo.presentation.splash.SplashView
import night.lines.todo.toothpick.DI
import night.lines.todo.ui.main.MainActivity
import toothpick.Toothpick

/**
 * Created by denisgabyshev on 18/03/2018.
 */
class SplashActivity : MvpAppCompatActivity(), SplashView {

    @InjectPresenter lateinit var splashPresenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter(): SplashPresenter =
            Toothpick.openScope(DI.APP_SCOPE)
                    .getInstance(SplashPresenter::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashPresenter.onViewPrepared()
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}