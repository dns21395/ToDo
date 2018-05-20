package night.lines.todo.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import night.lines.todo.ui.main.MainActivityModule
import night.lines.todo.ui.main.MainActivity
import night.lines.todo.ui.main.fragments.addtask.AddTaskFragmentProvider
import night.lines.todo.ui.main.fragments.task.TaskFragmentProvider

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        AddTaskFragmentProvider::class,
        TaskFragmentProvider::class
        ])
    @MainScope
    abstract fun bindMainActivity(): MainActivity

}