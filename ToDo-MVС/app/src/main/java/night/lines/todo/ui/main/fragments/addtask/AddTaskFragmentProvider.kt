package night.lines.todo.ui.main.fragments.addtask

import dagger.Module
import dagger.android.ContributesAndroidInjector
import night.lines.todo.ui.main.fragments.addtask.controller.AddTaskFragment

@Module
abstract class AddTaskFragmentProvider {
    @ContributesAndroidInjector(modules = [AddTaskFragmentModule::class])
    internal abstract fun provideAddTaskFragment(): AddTaskFragment
}