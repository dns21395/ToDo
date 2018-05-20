package night.lines.todo


import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import night.lines.todo.dagger.application.DaggerApplicationComponent
import timber.log.Timber


/**
 * Created by denisgabyshev on 17/03/2018.
 */
class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        when(BuildConfig.DEBUG) {
<<<<<<< HEAD
            true -> Timber.plant(Timber.DebugTree())
            false -> {}
=======
            true -> Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
            false -> {
                Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
                FactoryRegistryLocator.setRootRegistry(night.lines.todo.FactoryRegistry())
                MemberInjectorRegistryLocator.setRootRegistry(night.lines.todo.MemberInjectorRegistry())
            }
>>>>>>> soso
        }
    }
}