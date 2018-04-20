package night.lines.todo

import android.app.Application
import night.lines.todo.toothpick.DI
import night.lines.todo.toothpick.module.AppModule
import night.lines.todo.toothpick.module.MainActivityModule
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

        initToothPick()
        initAppScope()
    }

    private fun initToothPick() {
        when(BuildConfig.DEBUG) {
            true -> Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
            false -> {
                Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
                FactoryRegistryLocator.setRootRegistry(night.lines.todo.app.FactoryRegistry())
                MemberInjectorRegistryLocator.setRootRegistry(night.lines.todo.app.MemberInjectorRegistry())
            }
        }
    }

    private fun initAppScope() {
        val appScope = Toothpick.openScope(DI.APP_SCOPE)
        appScope.installModules(AppModule(this))

        val mainScope = Toothpick.openScopes(DI.APP_SCOPE, DI.MAIN_SCOPE)
        mainScope.installModules(MainActivityModule())
    }


}