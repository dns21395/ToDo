package night.lines.todo

import android.app.Application
import night.lines.todo.toothpick.app.AppModule
import night.lines.todo.toothpick.app.AppScope
import night.lines.todo.toothpick.main.UseCaseModule
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator


/**
 * Created by denisgabyshev on 17/03/2018.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initTimber()
        initAppScope()
    }

    private fun initTimber() {
        when(BuildConfig.DEBUG) {
            true -> Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
            false -> {
                Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
                FactoryRegistryLocator.setRootRegistry(night.lines.todo.FactoryRegistry())
                MemberInjectorRegistryLocator.setRootRegistry(night.lines.todo.MemberInjectorRegistry())
            }
        }
    }

    private fun initAppScope() {
        val appScope = Toothpick.openScope(AppScope::class.java)
        appScope.installModules(
                AppModule(this),
                UseCaseModule())
    }
}