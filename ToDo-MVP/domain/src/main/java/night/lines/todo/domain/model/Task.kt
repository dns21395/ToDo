package night.lines.todo.domain.model

data class Task (
        val id: Long,
        val listId: Long?,
        val taskName: String,
        var date: Long,
        var isDone: Boolean = false
)

