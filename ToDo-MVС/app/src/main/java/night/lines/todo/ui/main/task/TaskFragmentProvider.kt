package night.lines.todo.ui.main.task

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class TaskFragmentProvider {
    @ContributesAndroidInjector(modules = [TaskFragmentModule::class])
    internal abstract fun provideTaskFragmentFactory(): TaskFragment
}