package night.lines.todo.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import night.lines.todo.ui.main.MainActivityModule
import night.lines.todo.ui.main.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}