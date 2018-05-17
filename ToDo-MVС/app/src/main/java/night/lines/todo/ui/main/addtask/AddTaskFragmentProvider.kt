package night.lines.todo.ui.main.addtask

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddTaskFragmentProvider {
    @ContributesAndroidInjector(modules = [AddTaskFragmentModule::class])
    internal abstract fun provideAddTaskFragment(): AddTaskFragment
}