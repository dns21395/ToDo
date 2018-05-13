package night.lines.todo.toothpick.provider

import android.content.Context
import night.lines.todo.data.prefs.PreferencesRepositoryImpl
import night.lines.todo.domain.repository.PreferencesRepository
import javax.inject.Inject
import javax.inject.Provider

class PreferencesRepositoryProvider @Inject constructor(private val context: Context): Provider<PreferencesRepository> {
    override fun get(): PreferencesRepository = PreferencesRepositoryImpl(context)
}