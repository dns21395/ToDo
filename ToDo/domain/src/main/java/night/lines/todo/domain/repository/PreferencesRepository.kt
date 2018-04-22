package night.lines.todo.domain.repository

interface PreferencesRepository {
    fun setFinishedTasksVisibility(status: Boolean)
    fun getFinishedTasksVisibility(): Boolean
}