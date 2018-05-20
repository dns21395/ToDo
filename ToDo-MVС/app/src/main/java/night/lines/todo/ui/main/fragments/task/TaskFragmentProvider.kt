package night.lines.todo.ui.main.fragments.task

import dagger.Module
import dagger.android.ContributesAndroidInjector
import night.lines.todo.ui.main.fragments.task.controller.TaskFragment

@Module
internal abstract class TaskFragmentProvider {
    @ContributesAndroidInjector(modules = [TaskFragmentModule::class])
    internal abstract fun provideTaskFragmentFactory(): TaskFragment
}